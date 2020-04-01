package com.pia.appetiser.test.data.source.remote

import com.pia.appetiser.test.data.entity.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("search")
    fun searchItunesMedia(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ): Single<PagedResponse<ResultResponse>>

}