package com.pia.appetiser.test.domain.baseusecase

import com.pia.appetiser.test.presentation.common.arch.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

abstract class NewCompletableUseCase<Params>(private val threadExecutor: ThreadExecutor) {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Completable

    fun getObservable(params: Params? = null): Completable =
        buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))

}