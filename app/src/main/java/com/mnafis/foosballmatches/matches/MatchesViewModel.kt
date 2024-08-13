package com.mnafis.foosballmatches.matches

import androidx.lifecycle.ViewModel
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.models.DateInfo
import com.mnafis.foosballmatches.models.Match
import io.reactivex.disposables.CompositeDisposable

class MatchesViewModel(
    private val matchesRepository: MatchesRepository
) : ViewModel() {
    fun doThis() {

    }
}