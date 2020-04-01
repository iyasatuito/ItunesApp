package com.pia.appetiser.test.presentation.common.rx

import androidx.appcompat.widget.SearchView
import io.reactivex.Observable

object RxSearchViewSupport {
  @JvmStatic
  fun changes(searchView: SearchView): Observable<out String> = ViewQueryChangeObservable(
      searchView
  )
}