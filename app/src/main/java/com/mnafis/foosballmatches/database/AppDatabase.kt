package com.mnafis.foosballmatches.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnafis.foosballmatches.database.matches.MatchesDAO
import com.mnafis.foosballmatches.database.players.PlayersDAO
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player

@Database(entities = [Player::class, Match::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayersDAO
    abstract fun matchesDao(): MatchesDAO
}