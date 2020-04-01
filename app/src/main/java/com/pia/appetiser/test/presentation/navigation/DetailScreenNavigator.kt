package com.pia.appetiser.test.presentation.navigation

import android.app.Activity
import com.pia.appetiser.test.data.entity.ResultResponse
import com.pia.appetiser.test.presentation.common.ext.transitionRightToLeft
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.ui.detail.DetailActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class DetailScreenNavigator @Inject constructor(private val activity: Activity){
    fun navigate(id: DisplayableItunesDetails){
        activity.apply {
            transitionRightToLeft(intentFor<DetailActivity>(DetailActivity.KEY_ITUNES_OBJECT to id))
        }
    }
}
