package com.mnafis.foosballmatches.database.players

import com.mnafis.foosballmatches.models.Player
import io.reactivex.Completable
import io.reactivex.Single

/**
 * This interface is going to be the blueprint of the repository implementation.
 * Using this, we can come up with different solutions to data storage. For now, Room is used
 * to store data locally with one of the implementation. For future, we can utilize this interface to
 * create an API integration to store data remotely
 */
interface PlayersRepository {
    fun addNewPlayer(player: Player) : Completable
    fun addNewPlayers(players: List<Player>) : Completable //this should only be used to add sample data, not for production
    fun updatePlayer(player: Player) : Completable
    fun deletePlayer(player: Player) : Completable
    fun deleteAllPlayers() : Completable
    fun getAllPlayers() : Single<List<Player>>
}