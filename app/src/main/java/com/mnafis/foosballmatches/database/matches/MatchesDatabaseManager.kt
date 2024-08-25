package com.mnafis.foosballmatches.database.matches

import com.mnafis.foosballmatches.database.AppDatabase
import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.tools.RxSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MatchesDatabaseManager @Inject constructor(
    appDatabase: AppDatabase,
    private val rxSchedulerProvider: RxSchedulerProvider
): MatchesRepository {
    private val matchesDAO: MatchesDAO = appDatabase.matchesDao()

    override fun addNewMatch(match: Match): Completable = Completable.fromCallable {
        matchesDAO.addNewMatch(match)
    }.addDataBaseSchedulers()

    override fun addNewMatches(matches: List<Match>) = Completable.fromCallable {
        matches.forEach { matchesDAO.addNewMatch(it) }
    }.addDataBaseSchedulers()

    override fun updateMatch(match: Match): Completable = Completable.fromCallable {
        matchesDAO.updateMatch(match)
    }.addDataBaseSchedulers()

    override fun deleteMatch(match: Match): Completable = Completable.fromCallable {
        matchesDAO.deleteMatch(match)
    }.addDataBaseSchedulers()

    override fun deleteAllMatches() = Completable.fromCallable {
        matchesDAO.deleteAllMatches()
    }.addDataBaseSchedulers()

    override fun getAllMatches(): Single<List<Match>> = Single.fromCallable {
        matchesDAO.getAllMatches()
    }.addDataBaseSchedulers()

    private fun Completable.addDataBaseSchedulers(): Completable =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.androidMainThread)

    private fun <T : Any> Single<T>.addDataBaseSchedulers(): Single<T> =
        this.subscribeOn(rxSchedulerProvider.ioScheduler)
            .observeOn(rxSchedulerProvider.androidMainThread)
}