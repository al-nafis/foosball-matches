package com.mnafis.foosballmatches.database.players

import com.mnafis.foosballmatches.database.AppDatabase
import com.mnafis.foosballmatches.models.Player
import com.mnafis.foosballmatches.tools.RxSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single

class PlayersDatabaseManager(
    appDatabase: AppDatabase,
    private val rxSchedulerProvider: RxSchedulerProvider
) : PlayersRepository {
    private val playersDAO: PlayersDAO = appDatabase.playersDao()

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
    }.addDataBaseSchedulers()

    override fun deleteAllPlayers() = Completable.fromCallable {
        playersDAO.deleteAllPlayers()
    }.addDataBaseSchedulers()

    override fun getAllPlayers() = Single.fromCallable {
        playersDAO.getAllPlayers()
    }.addDataBaseSchedulers()

    private fun Completable.addDataBaseSchedulers(): Completable =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.singleScheduler)

    private fun <T : Any> Single<T>.addDataBaseSchedulers(): Single<T> =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.singleScheduler)
}

data class PlayerIdExistsException(val id: Int) : Exception()