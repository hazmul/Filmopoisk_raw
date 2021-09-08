package com.ashush.filmopoisk_raw.domain.interactor

import com.ashush.filmopoisk_raw.domain.data_interfaces.IRepository
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response
import javax.inject.Inject

class Interactor @Inject constructor(
    private val repository: IRepository
) {

    suspend fun getConfiguration(api_key: String): Response<DataConfigurationModel> {
        return repository.getConfiguration(api_key)
    }

    suspend fun getGenresInfo(api_key: String): Response<DataGenresInfo> {
        return repository.getGenresInfo(api_key)
    }

    suspend fun getMovieDetail(
        movie_id: Int,
        api_key: String,
        append_to_response: String? = null,
    ): Response<DataMovieDetailModel> {
        return repository.getMovieDetail(movie_id, api_key, append_to_response)
    }

    suspend fun getMoviesPopular(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesPopular(api_key, language, page, region)
    }

    suspend fun getMoviesTopRated(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesTopRated(api_key, language, page, region)
    }

    suspend fun getMoviesUpcoming(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesUpcoming(api_key, language, page, region)
    }

    suspend fun getMoviesNowPlaying(
        api_key: String,
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesNowPlaying(api_key, language, page, region)
    }

    suspend fun getSearchResult(
        api_key: String,
        language: String? = null,
        query: String,
        page: String? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): Response<DataMoviesModel> {
        return repository.getSearchResult(
            api_key,
            language,
            query,
            page,
            include_adult,
            region,
            year,
            primary_release_year
        )
    }

    suspend fun getAll(dataType: DataType): List<DataMovieDetailModel>? {
        return repository.getDBHandler(dataType).getAll()
    }

    suspend fun getById(dataType: DataType, movieId: Int): DataMovieDetailModel? {
        return repository.getDBHandler(dataType).getById(movieId)
    }

    suspend fun insert(dataType: DataType, movie: DataMovieDetailModel) {
        repository.getDBHandler(dataType).insert(movie)
    }

    suspend fun delete(dataType: DataType, movie: DataMovieDetailModel) {
        repository.getDBHandler(dataType).delete(movie)
    }

    suspend fun updateMovie(dataType: DataType, movie: DataMovieDetailModel) {
        repository.getDBHandler(dataType).updateMovie(movie)
    }

}