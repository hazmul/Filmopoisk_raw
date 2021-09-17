package com.ashush.filmopoisk_raw.data.storage.db.entity

/**
 * Обобщенная таблица фильмов категории
 * Класс отражает структуру хранимых данных
 */

open class BaseEntity(
    open val adult: Boolean,
    open val backdropPath: String,
    open val genres: String,
    open val homepage: String,
    open val id: Int,
    open val originalLanguage: String,
    open val overview: String,
    open val posterPath: String,
    open val productionCompanies: String,
    open val productionCountries: String,
    open val releaseDate: String,
    open val tagline: String,
    open val title: String,
    open val voteAverage: Double,
)