package com.pia.appetiser.test.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RoomItunesDetail(

    @PrimaryKey val trackId: Long,
    val wrapperType: String,
    val kind: String?,
    val artistId: Long?,
    val artistName: String?,
    val trackName: String?,
    val artistViewUrl: String?,
    val trackViewUrl: String?,
    val previewUrl: String?,
    val artworkUrl100: String?,
    val collectionPrice: Double,
    val trackPrice: Double,
    val releaseDate: String,
    val trackTimeMillis: Int,
    val primaryGenreName: String,
    val contentAdvisoryRating : String?,
    val shortDescription : String?,
    val longDescription : String?
)