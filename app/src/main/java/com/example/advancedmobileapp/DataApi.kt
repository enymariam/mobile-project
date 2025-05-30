package com.example.advancedmobileapp


import com.example.advancedmobileapp.models.RestaurantDto
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingDto
import com.example.advancedmobileapp.models.RestaurantsDto
import retrofit2.http.GET
import retrofit2.http.Path


interface DataApi {
    // Restaurants with average ratings
    @GET("api/restaurants/ratings")
    suspend fun getRestaurantsWithAvgRatings(): List<RestaurantWithAvgRatingDto>

    // Ratings on individual restaurant
    @GET("api/restaurants/{id}/ratings")
    suspend fun getRestaurant(@Path("id") id: Int): List<RestaurantDto>  // Use of AI [3]
}