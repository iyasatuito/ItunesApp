package com.pia.appetiser.test.domain.baseusecase

import com.pia.appetiser.test.presentation.common.arch.PostExecutionThread
import com.pia.appetiser.test.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class NewSingleUseCaseWoParams<out T> (
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    protected abstract fun buildUseCaseSingle(): Single<out T>

    fun getObservable(): Single<out T> =
        buildUseCaseSingle()
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
}