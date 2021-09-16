package com.ashush.filmopoisk_raw.domain.datainterfaces

import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import com.ashush.filmopoisk_raw.domain.models.RequestResult

interface IStorageRepository {

    suspend fun storeAppConfiguration(config: AppConfig): RequestResult<Boolean>
    suspend fun getAppConfiguration(): RequestResult<AppConfig>

    suspend fun getAll(dataType: DataType): RequestResult<List<DomainDetailedMovie>>
    suspend fun getById(movieId: Int, dataType: DataType): RequestResult<DomainDetailedMovie>
    suspend fun insert(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Long>
    suspend fun delete(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Int>
    suspend fun updateMovie(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Int>
}