package com.pia.appetiser.test.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pia.appetiser.test.domain.model.db.RoomItunesDetail
import io.reactivex.Single

@Dao
abstract class ItunesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(list: List<RoomItunesDetail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: RoomItunesDetail)


    @Query("SELECT * FROM RoomItunesDetail")
    abstract fun getAll(): Single<List<RoomItunesDetail>>

    @Query("SELECT * FROM RoomItunesDetail WHERE kind = :filter" )
    abstract fun getAllMovies(filter: String): Single<List<RoomItunesDetail>>

    @Query("SELECT * FROM RoomItunesDetail WHERE kind = :filter")
    abstract fun getAllSongs(filter: String): Single<List<RoomItunesDetail>>

    @Query("SELECT * FROM RoomItunesDetail WHERE kind = :filter")
    abstract fun getAllTVShows(filter: String): Single<List<RoomItunesDetail>>


    open fun replaceAll(flatmates: List<RoomItunesDetail>) {
        clear()
        insertAll(flatmates)
    }

    @Query("DELETE FROM RoomItunesDetail")
    abstract fun clear()

}