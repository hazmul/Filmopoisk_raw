package com.ashush.filmopoisk_raw.domain.datainterfaces

import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.ashush.filmopoisk_raw.domain.models.RequestResult

interface IStorageHandler {
    suspend fun getAll(): RequestResult<List<DetailedMovie>>
    suspend fun getById(movieId: Int): RequestResult<DetailedMovie>
    suspend fun insert(movie: DetailedMovie): RequestResult<Long>
    suspend fun delete(movie: DetailedMovie): RequestResult<Int>
    suspend fun updateMovie(movie: DetailedMovie): RequestResult<Int>
}