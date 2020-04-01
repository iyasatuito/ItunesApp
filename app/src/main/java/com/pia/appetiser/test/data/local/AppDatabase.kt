package com.pia.appetiser.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pia.appetiser.test.domain.model.db.RoomItunesDetail


@Database(
    version = 1,
    entities = [
        RoomItunesDetail::class

    ], exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun itunesDataDao(): ItunesDataDao
}