package com.mnafis.foosballmatches.database.players

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mnafis.foosballmatches.models.Player

@Dao
interface PlayersDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addPlayer(player: Player)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updatePlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)

    @Query("DELETE FROM Player")
    fun deleteAllPlayers()

    @Query("SELECT * FROM Player ORDER BY employeeId")
    fun getAllPlayers(): List<Player>

    @Query("SELECT * FROM Player WHERE employeeId = :employeeId")
    fun getPlayerById(employeeId: Int): List<Player>
}