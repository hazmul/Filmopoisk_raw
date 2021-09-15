package com.ashush.filmopoisk_raw.domain.datainterfaces

import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import com.ashush.filmopoisk_raw.domain.models.RequestResult

interface IStorageRepository {

    suspend fun storeAppConfiguration(config: AppConfig): RequestResult<Boolean>
    suspend fun getAppConfiguration(): RequestResult<AppConfig>

    suspend fun getAll(dataType: DataType): RequestResult<List<DetailedMovie>>
    suspend fun getById(movieId: Int, dataType: DataType): RequestResult<DetailedMovie>
    suspend fun insert(movie: DetailedMovie, dataType: DataType): RequestResult<Long>
    suspend fun delete(movie: DetailedMovie, dataType: DataType): RequestResult<Int>
    suspend fun updateMovie(movie: DetailedMovie, dataType: DataType): RequestResult<Int>
}