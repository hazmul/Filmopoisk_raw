package com.ashush.filmopoisk_raw.domain.models

/**
 * Модель данных для использования в слое бизнес логики.
 * Отражает некоторую часть данных [currentPage] из общего набора данных [totalPages].
 * Содержит подборку фильмов [moviesList].
 */

data class DomainMovies(
    val currentPage: Int,
    val totalPages: Int,
    val moviesList: List<Movie>
) {
    data class Movie(
        val adult: Boolean,
        val posterPath: String,
        val genres: String,
        val genreIds: List<Int>,
        val id: Int,
        val overview: String,
        val releaseDate: String,
        val title: String,
        val voteAverage: Double,
    )
}