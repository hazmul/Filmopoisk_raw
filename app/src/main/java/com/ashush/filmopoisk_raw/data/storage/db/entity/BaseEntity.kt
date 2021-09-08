package com.ashush.filmopoisk_raw.data.storage.db.entity

abstract class BaseEntity {
    abstract val rowid: Int?
    abstract val movieId: Int?
    abstract val title: String?
    abstract val popularity: Double?
    abstract val releaseDate: String?
    abstract val overview: String?
    abstract val tagline: String?
    abstract val posterPath: String?
}