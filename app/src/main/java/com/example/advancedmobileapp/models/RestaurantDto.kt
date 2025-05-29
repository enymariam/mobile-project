package com.example.advancedmobileapp.models

import com.google.gson.annotations.SerializedName

data class RestaurantDto(
    val id: Int,
    @SerializedName("user_id") val userId: Int?,
    val value: Number?,
    val description: String?,
    @SerializedName("date_rated") val dateRated: String,
)

data class RestaurantState(
    val loading: Boolean = false,
    val error: String? = null,
    val theRestaurant:List<RestaurantDto> = emptyList()
)