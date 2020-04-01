package com.pia.appetiser.test.domain.appusecase

import com.pia.appetiser.test.data.entity.mapper.toDisplayableItunesDetails
import com.pia.appetiser.test.data.entity.mapper.toItunesDetailEntity
import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.baseusecase.NewSingleUseCase
import com.pia.appetiser.test.domain.params.SearchItunesParam
import com.pia.appetiser.test.presentation.common.arch.PostExecutionThread
import com.pia.appetiser.test.presentation.common.arch.ThreadExecutor
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import io.reactivex.Single
import javax.inject.Inject


class SearchItunesUseCase @Inject constructor(
    private val itunesRepository: ItunesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : NewSingleUseCase<SearchItunesParam, List<DisplayableItunesDetails>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: SearchItunesParam?): Single<List<DisplayableItunesDetails>> =
        itunesRepository.searchItunesTracks(params!!.term, params.country, params.media).map { it.results.map { it.toItunesDetailEntity() }.map { it.toDisplayableItunesDetails() } }

}