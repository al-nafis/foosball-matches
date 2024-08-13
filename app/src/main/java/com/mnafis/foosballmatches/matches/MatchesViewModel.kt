package com.mnafis.foosballmatches.matches

import androidx.lifecycle.ViewModel
import com.mnafis.foosballmatches.ExampleClass

class MatchesViewModel (
    private val exampleClass: ExampleClass
): ViewModel() {
    fun doThis() {
        exampleClass.doSomething()
    }
}