package com.mnafis.foosballmatches.database.matches

import com.mnafis.foosballmatches.models.Match
import io.reactivex.Completable
import io.reactivex.Single

/**
 * This interface is going to be the blueprint of the repository implementation.
 * Using this, we can come up with different solutions to data storage. For now, Room is used
 * to store data locally with one of the implementation. For future, we can utilize this interface to
 * create an API integration to store data remotely
 */
interface MatchesRepository {
    fun addNewMatch(match: Match) : Completable
    fun addNewMatches(matches: List<Match>) : Completable //this should only be used to add sample data, not for production
    fun updateMatch(updatedMatch: Match) : Completable
    fun deleteMatch(match: Match) : Completable
    fun deleteAllMatches() : Completable
    fun getAllMatches() : Single<List<Match>>
}