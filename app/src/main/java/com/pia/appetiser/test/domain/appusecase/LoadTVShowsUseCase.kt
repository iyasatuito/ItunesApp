package com.pia.appetiser.test.domain.appusecase

import com.pia.appetiser.test.data.entity.mapper.toDisplayableItunesDetails
import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.baseusecase.NewSingleUseCaseWoParams
import com.pia.appetiser.test.presentation.common.arch.PostExecutionThread
import com.pia.appetiser.test.presentation.common.arch.ThreadExecutor
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import io.reactivex.Single
import javax.inject.Inject

class LoadTVShowsUseCase @Inject constructor(
    private val itunesRepository: ItunesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : NewSingleUseCaseWoParams<List<DisplayableItunesDetails>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(): Single<List<DisplayableItunesDetails>> =
        itunesRepository.getTVShows().map { it.map { it.toDisplayableItunesDetails() } }

}