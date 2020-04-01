package com.pia.appetiser.test.presentation.common.rx

import android.annotation.SuppressLint
import androidx.appcompat.widget.SearchView
import com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable


internal class ViewQueryChangeObservable(private val searchView: SearchView) : Observable<String>() {

    @SuppressLint("RestrictedApi")
    override fun subscribeActual(observer: Observer<in String>) {
        if (!checkMainThread(observer)) {
            return
        }
        val listener = Listener(searchView, observer)
        observer.onSubscribe(listener)
        searchView.setOnQueryTextListener(listener)
    }

    internal class Listener(
        private val searchView: SearchView,
        private val observer: Observer<in String>
    ) : MainThreadDisposable(),
        SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String): Boolean {
            observer.onNext(query)
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            observer.onNext(newText)
            return false
        }

        override fun onDispose() {
            searchView.setOnQueryTextListener(null)
        }
    }
}