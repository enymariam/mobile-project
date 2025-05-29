package com.example.advancedmobileapp.models

data class RestaurantWithAvgRatingDto(
    val id: Int,
    val name: String,
    val cuisine: String,
    val price_range: String,
    val address: String,
    val open_status: String,
    val rating: Float?,
    val review_count: Int)

data class RatingsState(
    val loading: Boolean = false,
    val error: String? = null,
    val ratings:List<RestaurantWithAvgRatingDto> = emptyList()
)
