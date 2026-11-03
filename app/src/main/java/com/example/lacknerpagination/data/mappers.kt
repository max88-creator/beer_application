package com.example.lacknerpagination.data

import com.example.lacknerpagination.data.local.models.BeerEntity
import com.example.lacknerpagination.data.remote.BeerDto
import com.example.lacknerpagination.domain.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagLine = tagLine,
        first_breved = first_breved,
        description = description,
        image_url = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagLine = tagLine,
        first_breved = first_breved,
        description = description,
        image_url = image_url
    )
}