package com.mnafis.foosballmatches.di

import com.mnafis.foosballmatches.database.matches.MatchesDatabaseManager
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersDatabaseManager
import com.mnafis.foosballmatches.database.players.PlayersRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun matchesDatabaseManager(
        matchesDatabaseManager: MatchesDatabaseManager
    ): MatchesRepository

    @Binds
    abstract fun playersDatabaseManager(
        playersDatabaseManager: PlayersDatabaseManager
    ): PlayersRepository
}