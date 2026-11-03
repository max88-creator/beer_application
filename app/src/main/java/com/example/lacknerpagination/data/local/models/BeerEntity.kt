package com.example.lacknerpagination.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer_entity")
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagLine: String,
    val first_breved: String,
    val description: String,
    val image_url: String?
)