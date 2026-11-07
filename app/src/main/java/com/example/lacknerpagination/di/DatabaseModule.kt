package com.example.lacknerpagination.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.lacknerpagination.data.local.models.BeerEntity
import com.example.lacknerpagination.data.local.models.BeerRemoteMediator
import com.example.lacknerpagination.data.remote.BeerApi
import com.example.lacknerpagination.data_base.BeerDataBase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(BeerApi::class.java)
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
                beerDB.beerDao().pagingSource()
            }
        )
    }
}


