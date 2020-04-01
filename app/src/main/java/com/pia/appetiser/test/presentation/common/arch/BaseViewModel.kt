package com.pia.appetiser.test.presentation.common.arch

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected fun addToDisposables(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}