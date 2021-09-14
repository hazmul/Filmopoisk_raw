package com.ashush.filmopoisk_raw.data.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorites(
    @ColumnInfo(name = "adult") override val adult: Boolean,
    @ColumnInfo(name = "backdropPath") override val backdropPath: String,
    @ColumnInfo(name = "genres") override val genres: String,
    @ColumnInfo(name = "homepage") override val homepage: String,
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    override val id: Int,
    @ColumnInfo(name = "originalLanguage") override val originalLanguage: String,
    @ColumnInfo(name = "overview") override val overview: String,
    @ColumnInfo(name = "posterPath") override val posterPath: String,
    @ColumnInfo(name = "productionCompanies") override val productionCompanies: String,
    @ColumnInfo(name = "productionCountries") override val productionCountries: String,
    @ColumnInfo(name = "releaseDate") override val releaseDate: String,
    @ColumnInfo(name = "tagline") override val tagline: String,
    @ColumnInfo(name = "title") override val title: String,
    @ColumnInfo(name = "voteAverage") override val voteAverage: Double,
) : BaseEntity(
    adult,
    backdropPath,
    genres,
    homepage,
    id,
    originalLanguage,
    overview,
    posterPath,
    productionCompanies,
    productionCountries,
    releaseDate,
    tagline,
    title,
    voteAverage
)