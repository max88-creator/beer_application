package com.example.lacknerpagination.domain

data class Beer(
    val id: Int,
    val name: String,
    val tagLine: String,
    val first_breved: String,
    val description: String,
    val image_url: String?
)
