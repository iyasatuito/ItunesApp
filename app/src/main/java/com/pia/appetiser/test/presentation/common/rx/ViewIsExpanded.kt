package com.pia.appetiser.test.presentation.common.rx

import android.annotation.SuppressLint
import com.google.android.material.appbar.AppBarLayout
import com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable


internal class ViewIsExpanded(private val mAppbarLayout: AppBarLayout) : Observable<Boolean>() {


    @SuppressLint("RestrictedApi")
    override fun subscribeActual(observer: Observer<in Boolean>) {
        if (!checkMainThread(observer)) {
            return
        }

        val listener = Listener(mAppbarLayout,observer)
        observer.onSubscribe(listener)
        mAppbarLayout.addOnOffsetChangedListener(listener)
    }

    internal class Listener(
        private val appBarLayout: AppBarLayout,
        private val observer: Observer<in Boolean>
    ): MainThreadDisposable(), AppBarLayout.OnOffsetChangedListener {
        override fun onDispose() {
            appBarLayout.removeOnOffsetChangedListener(this)
        }

        override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
            if (Math.abs(verticalOffset) >= appBarLayout.height * .45) {
                observer.onNext(false)
            } else if (Math.abs(verticalOffset) < appBarLayout.height * .75) {
                observer.onNext(true)
            }
        }
    }

}