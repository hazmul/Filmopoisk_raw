package com.ashush.filmopoisk_raw.domain.repository

import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel

interface IRepository {

    fun getMoviesUpcoming(): DataMoviesModel?
    fun getMoviesPopular(): DataMoviesModel?
    fun getMoviesTopRated(): DataMoviesModel?
    fun getMoviesNowPlaying(): DataMoviesModel?
    fun getMovieDetail(
        id: Int,
        append_to_response: String? = null
    ): DataMovieDetailModel?

    fun getSearchResult(
        api_key: String,
        language: String? = null,
        query: String,
        page: String? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): DataMoviesModel?

    fun getConfiguration()

}