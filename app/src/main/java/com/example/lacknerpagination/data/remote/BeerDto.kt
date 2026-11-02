package com.example.lacknerpagination.data.remote

data class BeerDto(
    val id: Int,
    val name: String,
    val tagLine: String,
    val description: String,
    val first_breved: String,
    val image_url: String?
)