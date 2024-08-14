package com.mnafis.foosballmatches

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {
    private val _disposable = CompositeDisposable()

    infix fun addDisposable(disposable: Disposable) {
        _disposable.add(disposable)
    }

    fun disposeDisposables() {
        _disposable.dispose()
    }
}