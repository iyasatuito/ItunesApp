package com.pia.appetiser.test.data.entity

import com.google.gson.annotations.SerializedName

data class PagedResponse<Data>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Data>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)