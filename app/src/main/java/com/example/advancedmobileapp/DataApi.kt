package com.example.advancedmobileapp

import com.example.advancedmobileapp.models.RatingDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

// GET Ratings
interface DataApi {
    @GET("api/restaurants/ratings")
    suspend fun getRatings(): List<RatingDto>
}

val ratingsService = retrofit.create(DataApi::class.java)