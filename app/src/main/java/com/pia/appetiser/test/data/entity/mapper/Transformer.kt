package com.pia.appetiser.test.data.entity.mapper

import com.pia.appetiser.test.data.entity.ResultResponse
import com.pia.appetiser.test.domain.model.ItunesDetailEntity
import com.pia.appetiser.test.domain.model.db.RoomItunesDetail
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


fun ResultResponse.toItunesDetailEntity(): ItunesDetailEntity = ItunesDetailEntity(
    wrapperType = wrapperType,
    kind = kind,
    artistId = artistId,
    trackId = trackId,
    artistName = artistName,
    trackName = trackName,
    artistViewUrl = artistViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    artworkUrl100 = artworkUrl100,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    trackTimeMillis = trackTimeMillis,
    primaryGenreName = primaryGenreName,
    contentAdvisoryRating = contentAdvisoryRating,
    shortDescription = shortDescription,
    longDescription = longDescription
)

/**
 *Custom fields
 * @property coverImageUrl
 * @property itemImageUrl
 * Since available imgurls from the server are too small
 *
 */
fun ItunesDetailEntity.toDisplayableItunesDetails(): DisplayableItunesDetails =
    DisplayableItunesDetails(
        wrapperType = wrapperType,
        kind = kind,
        artistId = artistId,
        trackId = trackId,
        artistName = artistName,
        trackName = trackName,
        artistViewUrl = artistViewUrl,
        trackViewUrl = trackViewUrl,
        previewUrl = previewUrl,
        coverImageUrl = artworkUrl100?.replace("100x100","1000x1000"),
        itemImageUrl = artworkUrl100?.replace("100x100","500x500"),
        collectionPrice = collectionPrice,
        trackPriceString = "AU$ "+ DecimalFormat("#,###").format(trackPrice),
        releaseDate = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(releaseDate)),
        trackTimeMillis = trackTimeMillis,
        primaryGenreName = primaryGenreName,
        contentAdvisoryRating = contentAdvisoryRating,
        shortDescription = shortDescription,
        longDescription = longDescription
    )



fun RoomItunesDetail.toDetailEntity(): ItunesDetailEntity = ItunesDetailEntity(
    wrapperType = wrapperType,
    kind = kind,
    artistId = artistId,
    trackId = trackId,
    artistName = artistName,
    trackName = trackName,
    artistViewUrl = artistViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    artworkUrl100 = artworkUrl100,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    trackTimeMillis = trackTimeMillis,
    primaryGenreName = primaryGenreName,
    contentAdvisoryRating = contentAdvisoryRating,
    shortDescription = shortDescription,
    longDescription = longDescription
)

fun ItunesDetailEntity.toRoomItunesDetail(): RoomItunesDetail = RoomItunesDetail(
    wrapperType = wrapperType,
    kind = kind,
    artistId = artistId,
    trackId = trackId,
    artistName = artistName,
    trackName = trackName,
    artistViewUrl = artistViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    artworkUrl100 = artworkUrl100,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    trackTimeMillis = trackTimeMillis,
    primaryGenreName = primaryGenreName,
    contentAdvisoryRating = contentAdvisoryRating,
    shortDescription = shortDescription,
    longDescription = longDescription
)
