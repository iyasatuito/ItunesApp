package com.pia.appetiser.test.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pia.appetiser.test.data.source.remote.SearchItunesLoader
import com.pia.appetiser.test.domain.appusecase.SearchItunesUseCase
import com.pia.appetiser.test.presentation.common.arch.BaseViewModel
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import javax.inject.Inject

class SearchItunesViewModel @Inject constructor(
    private val searchItunesUseCase: SearchItunesUseCase
) : BaseViewModel() {


    var listing: LiveData<PagedList<DisplayableItunesDetails>> = object : LiveData<PagedList<DisplayableItunesDetails>>() {
    }
    private lateinit var sourceFactory: SearchItunesLoader.Factory


    fun searchMovieWithPage(term: String, country : String, media : String): LiveData<PagedList<DisplayableItunesDetails>>  {
        sourceFactory = SearchItunesLoader.Factory(
            searchItunesUseCase,
            term = term,
            country = country,
            media = media
        )

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20 * 2)
            .setEnablePlaceholders(true)
            .build()

        listing = LivePagedListBuilder<Int, DisplayableItunesDetails>(sourceFactory, config).build()
        listing.value?.dataSource?.invalidate()

        return listing

    }

    fun retry() {
        sourceFactory!!.sourceLiveData.value?.retry()
    }



}