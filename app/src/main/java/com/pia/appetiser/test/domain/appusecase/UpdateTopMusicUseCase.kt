package com.pia.appetiser.test.domain.appusecase

import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.baseusecase.NewCompletableUseCase
import com.pia.appetiser.test.presentation.common.arch.ThreadExecutor
import javax.inject.Inject

class UpdateTopMusicUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    private val repository: ItunesRepository
) : NewCompletableUseCase<Any?>(threadExecutor) {
    override fun buildUseCaseObservable(params: Any?) =
        run(repository::updateTopMusic)

}