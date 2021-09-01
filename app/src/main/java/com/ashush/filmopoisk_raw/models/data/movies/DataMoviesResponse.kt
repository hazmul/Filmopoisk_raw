package com.ashush.filmopoisk_raw.models.data.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//response for get popular
//response for get top rated
//response for get now playing
//response for get upcoming

@Serializable
data class DataMoviesResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("total_pages")
    val totalPages: Int
)

@Serializable
data class Result(
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("overview")
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("id")
    val id: Int,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("title")
    val title: String,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double
)