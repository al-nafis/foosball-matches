package com.mnafis.foosballmatches.database.players

import com.mnafis.foosballmatches.models.Player
import io.reactivex.Completable
import io.reactivex.Single

interface PlayersRepository {
    fun addNewPlayer(player: Player) : Completable
    fun addNewPlayers(players: List<Player>) : Completable //this should only be used to add sample data, not for production
    fun updatePlayer(player: Player) : Completable
    fun deletePlayer(player: Player) : Completable
    fun getAllPlayers() : Single<List<Player>>
}