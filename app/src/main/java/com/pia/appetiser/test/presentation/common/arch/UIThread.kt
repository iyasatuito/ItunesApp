package com.pia.appetiser.test.presentation.common.arch

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UIThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}