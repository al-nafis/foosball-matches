package com.mnafis.foosballmatches.di

import android.content.Context
import androidx.room.Room
import com.mnafis.foosballmatches.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedModule {
    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase::class.java.name).build()
}