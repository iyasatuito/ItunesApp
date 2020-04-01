package com.pia.appetiser.test.presentation.common.arch

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler
}