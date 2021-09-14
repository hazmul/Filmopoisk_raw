package com.ashush.filmopoisk_raw.domain.models

data class DetailedMovie(
    val adult: Boolean,
    val backdropPath: String,
    val genres: String,
    val homepage: String,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val productionCompanies: String,
    val productionCountries: String,
    val releaseDate: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
)