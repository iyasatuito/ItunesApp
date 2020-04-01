package com.pia.appetiser.test.presentation.common.rx

import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable

object RxRecyclerView {

    fun onScrollXChanges(recyclerView: RecyclerView): Observable<out Int> = ViewOnScrollChange(recyclerView)
    }