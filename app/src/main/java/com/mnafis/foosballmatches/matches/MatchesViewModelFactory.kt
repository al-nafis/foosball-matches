package com.mnafis.foosballmatches.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.database.matches.MatchesRepository
import javax.inject.Inject

class MatchesViewModelFactory @Inject constructor(
    private val matchesRepository: MatchesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MatchesViewModel(matchesRepository) as T
}