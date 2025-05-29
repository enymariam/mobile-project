package com.example.advancedmobileapp

import com.example.advancedmobileapp.models.RestaurantWithAvgRatingDto
import retrofit2.http.GET


interface DataApi {
    @GET("api/restaurants/ratings")
    suspend fun getRestaurantsWithAvgRatings(): List<RestaurantWithAvgRatingDto>
}