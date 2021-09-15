package com.ashush.filmopoisk_raw.domain.datainterfaces

import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.ashush.filmopoisk_raw.domain.models.Movies
import com.ashush.filmopoisk_raw.domain.models.RequestResult

interface IDataRepository {

    suspend fun getConfiguration(): RequestResult<Boolean>
    suspend fun getGenresInfo(): RequestResult<Boolean>

    suspend fun getMovieDetail(
        movie_id: Int,
        appendToResponse: String? = null,
    ): RequestResult<DetailedMovie>

    suspend fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies>

    suspend fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies>

    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies>

    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<Movies>

    suspend fun getSearchResult(
        language: String? = null,
        query: String,
        page: Int? = null,
        includeAdult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primaryReleaseYear: Int? = null
    ): RequestResult<Movies>

}