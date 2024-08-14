package com.mnafis.foosballmatches.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mnafis.foosballmatches.BaseViewModel
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import com.mnafis.foosballmatches.models.DateInfo
import com.mnafis.foosballmatches.models.Match
import io.reactivex.disposables.CompositeDisposable

class MatchesViewModel(
    matchesRepository: MatchesRepository
) : BaseViewModel() {

    private val _matches = MutableLiveData<List<Match>>()
    val matches: LiveData<List<Match>> = _matches

    init {
        this addDisposable matchesRepository.getAllMatches().subscribe(
            { items -> _matches.postValue(items) },
            { error -> error.printStackTrace() }
        )
    }

    fun disposeSubscriptions() {
        disposeDisposables()
    }
}