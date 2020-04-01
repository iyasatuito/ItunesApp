package com.pia.appetiser.test.presentation.common.rx

import com.google.android.material.appbar.AppBarLayout
import io.reactivex.Observable

object RxAppBar {

    fun offSetChanges(appBarLayout: AppBarLayout): Observable<out Int> = ViewOffSetOnSubscribe(appBarLayout)

    fun scrollRangeChanges(appBarLayout: AppBarLayout): Observable<out Int> = ViewScrollRangeOnSubscribe(appBarLayout)

    fun alphaValueChanges(appBarLayout: AppBarLayout): Observable<out Float> = ViewAlphaOnSubscribe(appBarLayout)

    fun isExpanded(appBarLayout: AppBarLayout): Observable<out Boolean> = ViewIsExpanded(appBarLayout)

}
