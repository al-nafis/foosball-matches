package com.mnafis.foosballmatches.database.matches

import com.mnafis.foosballmatches.database.AppDatabase
import com.mnafis.foosballmatches.tools.RxSchedulerProvider
import javax.inject.Inject

class MatchesDatabaseManager @Inject constructor(
    appDatabase: AppDatabase,
    private val rxSchedulerProvider: RxSchedulerProvider
) {
    private val matchesDAO: MatchesDAO = appDatabase.matchesDao()

    
}