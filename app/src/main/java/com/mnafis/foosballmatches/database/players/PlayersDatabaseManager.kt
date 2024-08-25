package com.mnafis.foosballmatches.database.players

import com.mnafis.foosballmatches.database.AppDatabase
import com.mnafis.foosballmatches.database.matches.MatchesDAO
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player
import com.mnafis.foosballmatches.tools.RxSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PlayersDatabaseManager @Inject constructor(
    appDatabase: AppDatabase,
    private val rxSchedulerProvider: RxSchedulerProvider
) : PlayersRepository {
    private val playersDAO: PlayersDAO = appDatabase.playersDao()
    private val matchesDAO: MatchesDAO = appDatabase.matchesDao()

    override fun addNewPlayer(player: Player): Completable = Single.fromCallable {
        playersDAO.getPlayerById(player.employeeId).isNotEmpty()
    }.flatMapCompletable { employeeIdExists ->
        if (employeeIdExists) {
            Completable.error(PlayerIdExistsException(player.employeeId))
        } else {
            Completable.fromCallable {
                playersDAO.addPlayer(player)
            }
        }
    }.addDataBaseSchedulers()

    override fun addNewPlayers(players: List<Player>) = Completable.fromCallable {
        players.forEach { playersDAO.addPlayer(it) }
    }.addDataBaseSchedulers()

    override fun updatePlayer(player: Player) = Completable.fromCallable {
        playersDAO.updatePlayer(player)
    }.addDataBaseSchedulers()

    override fun deletePlayer(player: Player) = Completable.fromCallable {
        playersDAO.deletePlayer(player)
    }.andThen(
        deleteAndGetMatchesPlayedByPlayer(player)
    ).flatMapCompletable { matches ->
        updateOpponentsStatsAfterPlayerDeletion(matches, player)
    }.addDataBaseSchedulers()

    override fun deleteAllPlayers() = Completable.fromCallable {
        playersDAO.deleteAllPlayers()
    }.addDataBaseSchedulers()

    override fun getAllPlayers() = Single.fromCallable {
        playersDAO.getAllPlayers()
    }.addDataBaseSchedulers()

    private fun deleteAndGetMatchesPlayedByPlayer(player: Player): Single<List<Match>> =
        Single.fromCallable {
            matchesDAO.getAllMatches()
        }.flatMapObservable { matches ->
            Observable.fromIterable(matches).filter {
                it.player1Id == player.employeeId || it.player2Id == player.employeeId
            }
        }.map { match ->
            matchesDAO.deleteMatch(match)
            match
        }.toList()

    private fun updateOpponentsStatsAfterPlayerDeletion(matches: List<Match>, player: Player): Completable =
        Observable.fromIterable(matches)
            .map { match ->
                val opponentId = if (match.player1Id == player.employeeId) {
                    match.player2Id
                } else {
                    match.player1Id
                }
                Pair(playersDAO.getPlayerById(opponentId).first(), match)
            }.flatMapCompletable { pair ->
                val (opponent, match) = pair
                Completable.fromCallable {
                    if (match.winnerId == opponent.employeeId) {
                        playersDAO.updatePlayer(
                            opponent.copy(
                                totalMatchesPlayed = (opponent.totalMatchesPlayed - 1)
                                    .coerceAtLeast(0),
                                wins = (opponent.wins - 1).coerceAtLeast(0)
                            )
                        )
                    } else {
                        playersDAO.updatePlayer(
                            opponent.copy(
                                totalMatchesPlayed = (opponent.totalMatchesPlayed - 1)
                                    .coerceAtLeast(0),
                                losses = (opponent.losses - 1).coerceAtLeast(0)
                            )
                        )
                    }
                }
            }

    private fun Completable.addDataBaseSchedulers(): Completable =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.androidMainThread)

    private fun <T : Any> Single<T>.addDataBaseSchedulers(): Single<T> =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.androidMainThread)
}

data class PlayerIdExistsException(val id: Int) : Exception()