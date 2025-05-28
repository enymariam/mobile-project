package com.example.advancedmobileapp

import com.example.advancedmobileapp.models.RatingDto
import retrofit2.http.GET


interface DataApi {
    @GET("api/restaurants/ratings")
    suspend fun getRatings(): List<RatingDto>
}