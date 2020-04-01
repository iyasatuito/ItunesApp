package com.pia.appetiser.test.presentation.common.rx

import com.google.android.material.appbar.AppBarLayout
import com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable


internal class ViewOffSetOnSubscribe(private val mAppbarLayout: AppBarLayout) : Observable<Int>() {


    override fun subscribeActual(observer: Observer<in Int>) {
        if (!checkMainThread(observer)) {
            return
        }

        val listener = Listener(mAppbarLayout,observer)
        observer.onSubscribe(listener)
        mAppbarLayout.addOnOffsetChangedListener(listener)
    }

    internal class Listener(
        private val appBarLayout: AppBarLayout,
        private val observer: Observer<in Int>
    ): MainThreadDisposable(), AppBarLayout.OnOffsetChangedListener {
        override fun onDispose() {
            appBarLayout.removeOnOffsetChangedListener(this)
        }

        override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
            observer.onNext(Math.abs(verticalOffset))
        }
    }

}
