package com.ashush.filmopoisk_raw.data.storage.db.entity

open class BaseEntity(
    open val movieId: Int?,
    open val title: String?,
    open val popularity: Double?,
    open val releaseDate: String?,
    open val overview: String?,
    open val tagline: String?,
    open val posterPath: String?,
    open val averageVote: Double?,
)