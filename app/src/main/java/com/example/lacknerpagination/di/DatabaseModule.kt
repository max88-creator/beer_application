package com.example.lacknerpagination.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.lacknerpagination.data.local.database.BeerDataBase
import com.example.lacknerpagination.data.local.models.BeerEntity
import com.example.lacknerpagination.data.local.models.BeerRemoteMediator
import com.example.lacknerpagination.data.remote.BeerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBeerDataBase(@ApplicationContext context: Context): BeerDataBase {
        return Room.databaseBuilder(
            context,
            BeerDataBase::class.java,
            "main_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): BeerApi {
return Retrofit.Builder()
    .baseUrl(BeerApi.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(
        beerDB: BeerDataBase, beerApi: BeerApi
    ): Pager<Int, BeerEntity> {
return Pager(
    config = PagingConfig(pageSize = 20),
    remoteMediator = BeerRemoteMediator(
        beerDb = beerDB,
        beerApi = beerApi
    ),
   pagingSourceFactory = {
       beerDB.dao.pagingSource()
   }
)
    }
}