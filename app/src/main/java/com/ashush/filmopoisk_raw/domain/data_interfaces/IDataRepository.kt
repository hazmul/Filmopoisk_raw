package com.ashush.filmopoisk_raw.domain.data_interfaces

import com.ashush.filmopoisk_raw.domain.interactor.DataType
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response

interface IDataRepository {

    suspend fun getConfiguration(): Response<DataConfigurationModel>
    suspend fun getGenresInfo(): Response<DataGenresInfo>

    suspend fun getMovieDetail(
        movie_id: Int,
        append_to_response: String? = null,
    ): Response<DataMovieDetailModel>

    suspend fun getMoviesPopular(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getMoviesTopRated(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel>

    suspend fun getSearchResult(
        language: String? = null,
        query: String,
        page: String? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): Response<DataMoviesModel>


    suspend fun getDBHandler(dataType: DataType): IDBHandler

}