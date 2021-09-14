package com.ashush.filmopoisk_raw.domain.interactor

import com.ashush.filmopoisk_raw.domain.datainterfaces.IDataRepository
import com.ashush.filmopoisk_raw.domain.models.*
import javax.inject.Inject

class Interactor @Inject constructor(
    private val dataRepository: IDataRepository
) {

    suspend fun getRemoteConfiguration(): RequestResult<Boolean> {
        return dataRepository.getConfiguration()
    }

    fun getDomainConfiguration(): DomainConfig {
        return DomainConfig.getInstance()
    }

    suspend fun getGenresInfo(): RequestResult<Boolean> {
        return dataRepository.getGenresInfo()
    }

    suspend fun getMovieDetail(
        movie_id: Int,
        append_to_response: String? = null,
    ): RequestResult<DetailedMovie> {
        return dataRepository.getMovieDetail(movie_id, append_to_response)
    }

    suspend fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies> {
        return dataRepository.getMoviesPopular(language, page, region)
    }

    suspend fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies> {
        return dataRepository.getMoviesTopRated(language, page, region)
    }

    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies> {
        return dataRepository.getMoviesUpcoming(language, page, region)
    }

    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies> {
        return dataRepository.getMoviesNowPlaying(language, page, region)
    }

    suspend fun getSearchResult(
        language: String? = null,
        query: String,
        page: Int? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): RequestResult<Movies> {
        return dataRepository.getSearchResult(
            language,
            query,
            page,
            include_adult,
            region,
            year,
            primary_release_year
        )
    }

    suspend fun getAll(dataType: DataType): RequestResult<List<DetailedMovie>> {
        return dataRepository.getStorageHandler(dataType).getAll()
    }

    suspend fun getById(dataType: DataType, movieId: Int): RequestResult<DetailedMovie> {
        return dataRepository.getStorageHandler(dataType).getById(movieId)
    }

    suspend fun insert(dataType: DataType, movie: DetailedMovie) {
        dataRepository.getStorageHandler(dataType).insert(movie)
    }

    suspend fun delete(dataType: DataType, movie: DetailedMovie): RequestResult<Int> {
        return dataRepository.getStorageHandler(dataType).delete(movie)
    }

    suspend fun updateMovie(dataType: DataType, movie: DetailedMovie): RequestResult<Int> {
        return dataRepository.getStorageHandler(dataType).updateMovie(movie)
    }

}