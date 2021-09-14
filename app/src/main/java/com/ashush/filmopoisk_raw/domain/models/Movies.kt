package com.ashush.filmopoisk_raw.domain.models

data class Movies(
    val currentPage: Int,
    val totalPages: Int,
    val moviesList: List<Movie>
) {
    data class Movie(
        val adult: Boolean,
        val posterPath: String,
        val genres: String,
        val genresId: List<Int>,
        val id: Int,
        val overview: String,
        val releaseDate: String,
        val title: String,
        val voteAverage: Double,
    )
}