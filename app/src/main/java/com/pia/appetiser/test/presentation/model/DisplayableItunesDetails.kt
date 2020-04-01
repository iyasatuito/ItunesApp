package com.pia.appetiser.test.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class DisplayableItunesDetails(
    val wrapperType: String,
    val kind: String?,
    val artistId: Long?,
    val trackId: Long,
    val artistName: String?,
    val trackName: String?,
    val artistViewUrl: String?,
    val trackViewUrl: String?,
    val previewUrl: String?,
    val coverImageUrl: String?,
    val itemImageUrl: String?,
    val collectionPrice: Double,
    val trackPriceString: String,
    val releaseDate: String,
    val trackTimeMillis: Int,
    val primaryGenreName: String,
    val contentAdvisoryRating : String?,
    val shortDescription : String?,
    val longDescription : String?

) : Parcelable