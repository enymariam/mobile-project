package com.example.advancedmobileapp.models

import com.google.gson.annotations.SerializedName

class RestaurantRatingsDto(
    val id: Int,
    @SerializedName("user_id") val userId: Int?,
    val value: Number?,
    val description: String?,
    @SerializedName("date_rated") val dateRated: String,
)

data class RestaurantRatingsState(
    val loading: Boolean = false,
    val error: String? = null,
    val restaurantRatings:List<RestaurantRatingsDto> = emptyList()
)