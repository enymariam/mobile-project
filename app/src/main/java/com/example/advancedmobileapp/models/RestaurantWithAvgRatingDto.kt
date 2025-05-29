package com.example.advancedmobileapp.models

import com.google.gson.annotations.SerializedName

data class RestaurantWithAvgRatingDto(
    val id: Int,
    val name: String,
    val cuisine: String,
    @SerializedName("price_range") val priceRange: String,
    val address: String,
    @SerializedName("open_status") val openStatus: String,
    val rating: Float?,
    @SerializedName("review_count") val reviewCount: Int
)

data class RatingsState(
    val loading: Boolean = false,
    val error: String? = null,
    val ratings:List<RestaurantWithAvgRatingDto> = emptyList()
)
