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

data class RestaurantWithAvgRatingsState(
    val loading: Boolean = false,
    val error: String? = null,
    val restaurantsRatings:List<RestaurantWithAvgRatingDto> = emptyList()
)

data class RestaurantDto(
    val id: Int,
    @SerializedName("user_id") val userId: Int?,
    val value: Number?,
    val description: String?,
    @SerializedName("date_rated") val dateRated: String,
)

data class RestaurantsDto(
    val name: String,
    val review: List<RestaurantDto> // Use of AI [3]
)

data class RestaurantState(
    val error: String? = null,
    val loading: Boolean = false,
    val theRestaurant: RestaurantsDto? = null
)
