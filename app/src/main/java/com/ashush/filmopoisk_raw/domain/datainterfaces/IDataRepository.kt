package com.ashush.filmopoisk_raw.domain.datainterfaces

import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.ashush.filmopoisk_raw.domain.models.RequestResult

interface IDataRepository {

    suspend fun getConfiguration(): RequestResult<Boolean>
    suspend fun getGenresInfo(): RequestResult<Boolean>

    suspend fun getMovieDetail(
        movie_id: Int,
        appendToResponse: String? = null,
    ): RequestResult<DomainDetailedMovie>

    suspend fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    suspend fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    suspend fun getSearchResult(
        language: String? = null,
        query: String,
        page: Int? = null,
        includeAdult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primaryReleaseYear: Int? = null
    ): RequestResult<DomainMovies>

}