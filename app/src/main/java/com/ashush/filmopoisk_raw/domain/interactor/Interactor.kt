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

    suspend fun getConfiguration(): Response<DataConfigurationModel> {
        return repository.getConfiguration()
    }

    suspend fun getGenresInfo(): Response<DataGenresInfo> {
        return repository.getGenresInfo()
    }

    suspend fun getMovieDetail(
        movie_id: Int,
        append_to_response: String? = null,
    ): Response<DataMovieDetailModel> {
        return repository.getMovieDetail(movie_id, append_to_response)
    }

    suspend fun getMoviesPopular(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesPopular(language, page, region)
    }

    suspend fun getMoviesTopRated(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesTopRated(language, page, region)
    }

    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesUpcoming(language, page, region)
    }

    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: String? = null,
        region: String? = null
    ): Response<DataMoviesModel> {
        return repository.getMoviesNowPlaying(language, page, region)
    }

    suspend fun getSearchResult(
        language: String? = null,
        query: String,
        page: String? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): Response<DataMoviesModel> {
        return repository.getSearchResult(
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

    suspend fun delete(dataType: DataType, movie: DataMovieDetailModel): Int {
        return repository.getDBHandler(dataType).delete(movie)
    }

    suspend fun updateMovie(dataType: DataType, movie: DataMovieDetailModel): Int {
        return repository.getDBHandler(dataType).updateMovie(movie)
    }

}