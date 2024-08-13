package com.mnafis.foosballmatches.database.players

import com.mnafis.foosballmatches.models.Player
import io.reactivex.Completable
import io.reactivex.Single

interface PlayersRepository {
    fun addNewPlayer(player: Player) : Completable
    fun updatePlayer(player: Player) : Completable
    fun deletePlayer(player: Player) : Completable
    fun getAllPlayers() : Single<List<Player>>
}