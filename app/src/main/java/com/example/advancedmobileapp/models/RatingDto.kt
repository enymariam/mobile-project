package com.example.advancedmobileapp.models

data class RatingDto(val id: Int, val name: String, val rating: Float, val reviewCount: Int)

data class RatingsState(
    val loading: Boolean = false,
    val error: String? = null,
    val ratings:List<RatingDto> = emptyList()
)
