package com.mnafis.foosballmatches.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mnafis.foosballmatches.ExampleClass
import javax.inject.Inject

class MatchesViewModelFactory @Inject constructor(
    private val exampleClass: ExampleClass
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MatchesViewModel(exampleClass) as T
}