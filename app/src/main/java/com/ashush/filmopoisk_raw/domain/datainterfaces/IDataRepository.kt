package com.ashush.filmopoisk_raw.domain.datainterfaces

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ashush.filmopoisk_raw.models.domain.DataType
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

    fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>>

    fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>>

    fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>>

    fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>>

    fun getSearchResult(
        language: String? = null,
        query: String,
        page: Int? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>>

    suspend fun getStorageHandler(dataType: DataType): IStorageHandler

}