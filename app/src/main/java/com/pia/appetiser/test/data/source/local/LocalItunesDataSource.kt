package com.pia.appetiser.test.data.source.local

import com.pia.appetiser.test.data.entity.mapper.toDetailEntity
import com.pia.appetiser.test.data.entity.mapper.toRoomItunesDetail
import com.pia.appetiser.test.data.local.AppDatabase
import com.pia.appetiser.test.domain.model.ItunesDetailEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 * All database related transactions
 *
 */


class LocalItunesDataSource @Inject constructor(appDatabase: AppDatabase) {

    private val dao = appDatabase.itunesDataDao()


    fun getAll(): Single<List<ItunesDetailEntity>> = dao
        .getAll()
        .map { it.map { it.toDetailEntity() } }


    fun getAllMovies(): Single<List<ItunesDetailEntity>> = dao
        .getAllMovies("feature-movie")
        .map { it.map { it.toDetailEntity() } }

    fun getAllSongs(): Single<List<ItunesDetailEntity>> = dao
        .getAllSongs("song")
        .map { it.map { it.toDetailEntity() } }

    fun getAllTVShows(): Single<List<ItunesDetailEntity>> = dao
        .getAllTVShows("tv-episode")
        .map { it.map { it.toDetailEntity() } }

    fun insert(list: ItunesDetailEntity) = list
        .run { toRoomItunesDetail() }
        .run(dao::insert)

    fun insertAll(list: List<ItunesDetailEntity>) = list
        .run{map { it.toRoomItunesDetail() }}
        .run(dao::insertAll)

    fun replaceAll(list: List<ItunesDetailEntity>) = list
        .run { map { it.toRoomItunesDetail() } }
        .run(dao::replaceAll)

}