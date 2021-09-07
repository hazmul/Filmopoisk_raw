package com.ashush.filmopoisk_raw.domain.repository

import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response

interface IRepository {

    suspend fun getConfiguration(api_key: String): Response<DataConfigurationModel>
    suspend fun getGenresInfo(api_key: String): Response<DataGenresInfo>

    suspend fun getMovieDetail(
        movie_id: Int,
        api_key: String,
        append_to_response: String? = null,
    ): Response<DataMovieDetailModel>

    suspend fun getMoviesPopular(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getMoviesTopRated(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getMoviesUpcoming(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getMoviesNowPlaying(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getSearchResult(
        api_key: String,
        language: String? = null,
        query: String,
        page: String? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): Response<DataMoviesModel>

    fun <T: BaseEntity> getAll(): List<BaseEntity>?
    fun <T: BaseEntity> getAllByIds(moviesId: List<Int>): List<BaseEntity>?
    fun <T: BaseEntity> updateMovies(moviesList: List<BaseEntity>)
    fun <T: BaseEntity> getById(movieId: Int): BaseEntity?
    fun <T: BaseEntity> delete(movieList: BaseEntity)
    fun <T: BaseEntity> insertAll(moviesList: List<T>)

}