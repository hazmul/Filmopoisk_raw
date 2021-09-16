package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.mapper.EntitiesMapper
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storage: IStorage
) : IStorageRepository {


    override suspend fun storeAppConfiguration(config: AppConfig): RequestResult<Boolean> {
        val result = storage.storeAppConfiguration(config)
        return RequestResult.Success(result)
    }


    override suspend fun getAppConfiguration(): RequestResult<AppConfig> {
        val result = storage.getAppConfiguration()
        return RequestResult.Success(result)
    }

    override suspend fun getAll(dataType: DataType): RequestResult<List<DomainDetailedMovie>> {
        val result = when (dataType) {
            DataType.FAVORITES -> storage.getFavoriteDao().getAll()
            DataType.WATCHLIST -> storage.getWatchlistDao().getAll()
        }
        return when {
            result != null -> RequestResult.Success(result.map { EntitiesMapper.mapToDetailMovie(it) })
            else -> RequestResult.Error(null, "Something wrong")
        }
    }

    override suspend fun getById(movieId: Int, dataType: DataType): RequestResult<DomainDetailedMovie> {
        val result = when (dataType) {
            DataType.FAVORITES -> storage.getFavoriteDao().getById(movieId)
            DataType.WATCHLIST -> storage.getWatchlistDao().getById(movieId)
        }
        return when {
            result != null -> RequestResult.Success(EntitiesMapper.mapToDetailMovie(result))
            else -> RequestResult.Error(null, "Something wrong")
        }
    }

    override suspend fun insert(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Long> {
        val result = when (dataType) {
            DataType.FAVORITES -> storage.getFavoriteDao().insert(EntitiesMapper.mapToFavorites(movieDomain))
            DataType.WATCHLIST -> storage.getWatchlistDao().insert(EntitiesMapper.mapToWatchlist(movieDomain))
        }
        return when {
            result >= 0 -> RequestResult.Success(result)
            else -> RequestResult.Error(null, "Something wrong")
        }
    }

    override suspend fun delete(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Int> {
        val result = when (dataType) {
            DataType.FAVORITES -> storage.getFavoriteDao().delete(EntitiesMapper.mapToFavorites(movieDomain))
            DataType.WATCHLIST -> storage.getWatchlistDao().delete(EntitiesMapper.mapToWatchlist(movieDomain))
        }
        return when {
            result >= 0 -> RequestResult.Success(result)
            else -> RequestResult.Error(null, "Something wrong")
        }
    }

    override suspend fun updateMovie(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Int> {
        val result = when (dataType) {
            DataType.FAVORITES -> storage.getFavoriteDao().update(EntitiesMapper.mapToFavorites(movieDomain))
            DataType.WATCHLIST -> storage.getWatchlistDao().update(EntitiesMapper.mapToWatchlist(movieDomain))
        }
        return when {
            result >= 0 -> RequestResult.Success(result)
            else -> RequestResult.Error(null, "Something wrong")
        }
    }

}