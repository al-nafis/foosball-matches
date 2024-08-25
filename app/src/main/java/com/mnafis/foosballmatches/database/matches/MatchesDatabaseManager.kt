package com.mnafis.foosballmatches.database.matches

import com.mnafis.foosballmatches.database.AppDatabase
import com.mnafis.foosballmatches.database.players.PlayersDAO
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.tools.RxSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MatchesDatabaseManager @Inject constructor(
    appDatabase: AppDatabase,
    private val rxSchedulerProvider: RxSchedulerProvider
): MatchesRepository {
    private val matchesDAO: MatchesDAO = appDatabase.matchesDao()
    private val playersDAO: PlayersDAO = appDatabase.playersDao()

    override fun addNewMatch(match: Match): Completable = Completable.fromCallable {
        matchesDAO.addNewMatch(match)
    }.andThen(
        updatePlayersStats(match, UpdateStatType.ADD)
    ).addDataBaseSchedulers()

    override fun addNewMatches(matches: List<Match>) = Completable.fromCallable {
        matches.forEach { matchesDAO.addNewMatch(it) }
    }.addDataBaseSchedulers()

    override fun updateMatch(updatedMatch: Match): Completable = Single.fromCallable {
        matchesDAO.getMatchById(updatedMatch.id).first()
    }.flatMapCompletable { originalMatch ->
        val updateMatch = Completable.fromCallable { matchesDAO.updateMatch(updatedMatch) }
        if (originalMatch.winnerId != updatedMatch.winnerId) {
            val updatePlayers = updatePlayersStats(updatedMatch, UpdateStatType.UPDATE_WINNER_SWAPPED)
            Completable.concatArray(updateMatch, updatePlayers)
        } else updateMatch
    }.addDataBaseSchedulers()

    override fun deleteMatch(match: Match): Completable = Completable.fromCallable {
        matchesDAO.deleteMatch(match)
    }.andThen(
        updatePlayersStats(match, UpdateStatType.DELETE)
    ).addDataBaseSchedulers()

    override fun deleteAllMatches() = Completable.fromCallable {
        matchesDAO.deleteAllMatches()
    }.addDataBaseSchedulers()

    override fun getAllMatches(): Single<List<Match>> = Single.fromCallable {
        matchesDAO.getAllMatches()
    }.addDataBaseSchedulers()

    private fun updatePlayersStats(
        match: Match,
        updateStatType: UpdateStatType
    ): Completable =
        Single.fromCallable {
            playersDAO.getAllPlayers()
        }.flatMapCompletable { players ->
            val (winner, loser) = if (match.player1Id == match.winnerId) {
                Pair(
                    players.find { it.employeeId == match.player1Id }!!,
                    players.find { it.employeeId == match.player2Id }!!
                )
            } else {
                Pair(
                    players.find { it.employeeId == match.player2Id }!!,
                    players.find { it.employeeId == match.player1Id }!!
                )
            }

            val updateWinner = Completable.fromCallable {
                playersDAO.updatePlayer(
                    when (updateStatType) {
                        UpdateStatType.ADD ->
                            winner.copy(
                                totalMatchesPlayed = winner.totalMatchesPlayed + 1,
                                wins = winner.wins + 1
                            )

                        UpdateStatType.DELETE ->
                            winner.copy(
                                totalMatchesPlayed = (winner.totalMatchesPlayed - 1).coerceAtLeast(0),
                                wins = (winner.wins - 1).coerceAtLeast(0)
                            )

                        UpdateStatType.UPDATE_WINNER_SWAPPED ->
                            winner.copy(
                                wins = winner.wins + 1,
                                losses = (winner.losses - 1).coerceAtLeast(0)
                            )
                    }
                )
            }

            val updateLoser = Completable.fromCallable {
                playersDAO.updatePlayer(
                    when (updateStatType) {
                        UpdateStatType.ADD ->
                            loser.copy(
                                totalMatchesPlayed = loser.totalMatchesPlayed + 1,
                                losses = loser.losses + 1
                            )

                        UpdateStatType.DELETE ->
                            loser.copy(
                                totalMatchesPlayed = (loser.totalMatchesPlayed - 1).coerceAtLeast(0),
                                losses = (loser.losses - 1).coerceAtLeast(0)
                            )

                        UpdateStatType.UPDATE_WINNER_SWAPPED ->
                            loser.copy(
                                wins = (loser.wins - 1).coerceAtLeast(0),
                                losses = loser.losses + 1
                            )
                    }
                )
            }

            Completable.concatArray(updateWinner, updateLoser)
        }

    private fun Completable.addDataBaseSchedulers(): Completable =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.androidMainThread)

    private fun <T : Any> Single<T>.addDataBaseSchedulers(): Single<T> =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.androidMainThread)

    enum class UpdateStatType {
        ADD, DELETE, UPDATE_WINNER_SWAPPED
    }
}