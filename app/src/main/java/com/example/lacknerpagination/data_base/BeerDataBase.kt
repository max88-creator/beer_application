package com.example.lacknerpagination.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lacknerpagination.data.local.dao.BeerDao
import com.example.lacknerpagination.data.local.models.BeerEntity

@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)

abstract class BeerDataBase: RoomDatabase() {
    abstract fun beerDao(): BeerDao
}