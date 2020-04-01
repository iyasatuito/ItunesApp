package com.pia.appetiser.test.presentation.common.arch

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import javax.inject.Inject

class UIThreadExecutor @Inject constructor() : Executor {
    private val mHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mHandler.post(command)
    }
}