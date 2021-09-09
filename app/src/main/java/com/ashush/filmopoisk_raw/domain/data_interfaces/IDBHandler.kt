package com.ashush.filmopoisk_raw.domain.data_interfaces

import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel

interface IDBHandler {
   suspend fun getAll(): List<DataMovieDetailModel>?
   suspend fun getById(movieId: Int): DataMovieDetailModel?
   suspend fun insert(movie: DataMovieDetailModel)
   suspend fun delete(movie: DataMovieDetailModel): Int
   suspend fun updateMovie(movie: DataMovieDetailModel): Int
}