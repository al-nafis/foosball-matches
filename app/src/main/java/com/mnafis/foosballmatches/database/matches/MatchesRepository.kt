package com.mnafis.foosballmatches.database.matches

import com.mnafis.foosballmatches.models.Match
import io.reactivex.Completable
import io.reactivex.Single

interface MatchesRepository {
    fun addNewMatch(match: Match) : Completable
    fun updateMatch(match: Match) : Completable
    fun deleteMatch(match: Match) : Completable
    fun getAllMatches() : Single<List<Match>>
}