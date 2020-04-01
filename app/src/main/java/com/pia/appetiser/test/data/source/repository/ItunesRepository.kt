package com.pia.appetiser.test.data.source.repository

import com.pia.appetiser.test.data.entity.PagedResponse
import com.pia.appetiser.test.data.entity.ResultResponse
import com.pia.appetiser.test.domain.model.ItunesDetailEntity
import io.reactivex.Completable
import io.reactivex.Single

interface ItunesRepository {

    fun getTVShows(): Single<List<ItunesDetailEntity>>

    fun getFeaturedMovies(): Single<List<ItunesDetailEntity>>

    fun nowShowingMovies(term: String, country : String, media : String): Single<PagedResponse<ResultResponse>>

    fun getTopMusic(): Single<List<ItunesDetailEntity>>

    fun searchItunesTracks(term: String, country : String, media : String): Single<PagedResponse<ResultResponse>>

    fun updateTVShows(): Completable

    fun updateFeaturedMovies(): Completable

    fun updateTopMusic(): Completable

}