package com.example.advancedmobileapp

import com.example.advancedmobileapp.models.RestaurantRatingsDto
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingDto
import retrofit2.http.GET


interface DataApi {
    // Restaurants with average ratings
    @GET("api/restaurants/ratings")
    suspend fun getRestaurantsWithAvgRatings(): List<RestaurantWithAvgRatingDto>

    // Ratings on individual restaurant
    @GET("api/restaurants/{id}ratings")
    suspend fun getRestaurantRatings(): List<RestaurantRatingsDto>
}