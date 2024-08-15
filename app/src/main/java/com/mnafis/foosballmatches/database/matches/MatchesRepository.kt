package com.mnafis.foosballmatches.database.matches

import com.mnafis.foosballmatches.models.Match
import com.mnafis.foosballmatches.models.Player
import io.reactivex.Completable
import io.reactivex.Single

interface MatchesRepository {
    fun addNewMatch(match: Match) : Completable
    fun addNewMatches(matches: List<Match>) : Completable //this should only be used to add sample data, not for production
    fun updateMatch(match: Match) : Completable
    fun deleteMatch(match: Match) : Completable
    fun deleteAllMatches() : Completable
    fun getAllMatches() : Single<List<Match>>
}