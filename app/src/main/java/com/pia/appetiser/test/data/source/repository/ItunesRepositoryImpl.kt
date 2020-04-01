package com.pia.appetiser.test.data.source.repository


import android.util.Log
import com.pia.appetiser.test.data.entity.*
import com.pia.appetiser.test.data.entity.mapper.toItunesDetailEntity
import com.pia.appetiser.test.data.source.local.LocalItunesDataSource
import com.pia.appetiser.test.data.source.remote.RemoteItunesDataSource
import com.pia.appetiser.test.domain.model.ItunesDetailEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ItunesRepositoryImpl @Inject constructor(
    private val remoteItunesDataSource: RemoteItunesDataSource,
    private val localItunesDataSource: LocalItunesDataSource
) : ItunesRepository {

    override fun searchItunesTracks(
        term: String,
        country: String,
        media: String
    ): Single<PagedResponse<ResultResponse>> = remoteItunesDataSource.searchItunesMovie(term, country, media)

    override fun nowShowingMovies(
        term: String,
        country: String,
        media: String
    ): Single<PagedResponse<ResultResponse>> =
        remoteItunesDataSource.noShowingMovies(term, country, media)
            .doOnSuccess { localItunesDataSource.insertAll(it.results.map { it.toItunesDetailEntity() }) }

    /**
     *
     *
     * The following methods are the functions that retrieve data from room database.
     *
     * @property getFeaturedMovies fetches all the stored featured movies
     * @property getTVShows fetches all the stored TV shows under "star"
     * @property getTopMusic fetches all top music stored in our database
     */

    override fun getFeaturedMovies(): Single<List<ItunesDetailEntity>> = localItunesDataSource.getAllMovies()

    override fun getTVShows( ): Single<List<ItunesDetailEntity>> = localItunesDataSource.getAllTVShows()

    override fun getTopMusic(): Single<List<ItunesDetailEntity>> = localItunesDataSource.getAllSongs()


    /**
     *
     *
     * The following methods are the functions that retrieve data from remote source and storing it to database.
     *
     * @param term the URL-encoded text string you want to search for
     * @param country the two-letter country code for the store you want to search. We'll use "au"
     * @param media the type of media we are fetching
     */

    override fun updateTopMusic(): Completable  =
        remoteItunesDataSource.getTopMusic("top", "au", "music")
            .doOnSuccess { localItunesDataSource.insertAll(it.results.map { it.toItunesDetailEntity() }) }
            .ignoreElement()

    override fun updateTVShows(): Completable =
        remoteItunesDataSource.getTVShows("star", "au", "tvShow")
            .doOnSuccess { localItunesDataSource.insertAll(it.results.map { it.toItunesDetailEntity() }) }
            .ignoreElement()

    override fun updateFeaturedMovies(): Completable =
        remoteItunesDataSource.getFeaturedMovies("heart", "au", "movie")
            .doOnSuccess { localItunesDataSource.insertAll(it.results.map { it.toItunesDetailEntity() }) }
            .ignoreElement()
}