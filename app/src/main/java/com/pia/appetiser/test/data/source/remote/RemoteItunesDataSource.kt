package com.pia.appetiser.test.data.source.remote

import javax.inject.Inject

class RemoteItunesDataSource @Inject constructor(
    private val apiService: ApiService
) {

    fun getTVShows(term: String, country : String, media : String) =
            apiService.searchItunesMedia(term, country, media)

    fun getFeaturedMovies(term: String, country : String, media : String) =
            apiService.searchItunesMedia(term, country, media)

    fun noShowingMovies(term: String, country : String, media : String) =
        apiService.searchItunesMedia(term, country, media)

    fun getTopMusic(term: String, country : String, media : String) =
        apiService.searchItunesMedia(term, country, media)

    fun searchItunesMovie(term: String, country : String, media : String) =
        apiService.searchItunesMedia(term, country, media)
}