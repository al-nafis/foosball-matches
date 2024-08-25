package com.mnafis.foosballmatches.database.matches

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mnafis.foosballmatches.models.Match

@Dao
interface MatchesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewMatch(match: Match)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMatch(match: Match)

    @Delete
    fun deleteMatch(match: Match)

    @Query("DELETE FROM `Match`")
    fun deleteAllMatches()

    @Query("SELECT * FROM `Match`")
    fun getAllMatches(): List<Match>

    @Query("SELECT * FROM `Match` WHERE id = :id")
    fun getMatchById(id: Long): List<Match>
}