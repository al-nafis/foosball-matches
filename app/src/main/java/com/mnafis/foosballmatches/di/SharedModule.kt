package com.mnafis.foosballmatches.di

import android.content.Context
import androidx.room.Room
import com.mnafis.foosballmatches.database.AppDatabase
import com.mnafis.foosballmatches.database.matches.MatchesDatabaseManager
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.database.players.PlayersDatabaseManager
import com.mnafis.foosballmatches.database.players.PlayersRepository
import com.mnafis.foosballmatches.tools.RxSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedModule {
    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase::class.java.name).build()

    @Provides
    fun matchesRepository(
        appDatabase: AppDatabase,
        rxSchedulerProvider: RxSchedulerProvider
    ): MatchesRepository =
        MatchesDatabaseManager(appDatabase, rxSchedulerProvider)

    @Provides
    fun playersRepository(
        appDatabase: AppDatabase,
        rxSchedulerProvider: RxSchedulerProvider
    ): PlayersRepository =
        PlayersDatabaseManager(appDatabase, rxSchedulerProvider)
}