package com.example.lacknerpagination.data.local.models

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.example.lacknerpagination.data.remote.BeerApi
import com.example.lacknerpagination.data.toBeerEntity
import com.example.lacknerpagination.data_base.BeerDataBase
import kotlinx.coroutines.delay
import okio.IOException
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDb: BeerDataBase,
    private val beerApi: BeerApi
) : RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            delay(2000.milliseconds)
            val beers = beerApi.getBeers(
                page = loadKey,
                pageCount = state.config.pageSize
            )
            beerDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beerDb.beerDao().clearAll()
                }
                val beerEntities = beers.map { it.toBeerEntity() }
                beerDb.beerDao().upsertAll(beerEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = beers.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}