package com.ashush.filmopoisk_raw.data.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity(tableName = "watchlist")
class Watchlist(
    @PrimaryKey(autoGenerate = true)
    override val rowid: Int,
    @ColumnInfo(name = "movie_id") override val movieId: Int?,
    @ColumnInfo(name = "title") override val title: String?,
    @ColumnInfo(name = "popularity") override val popularity: Double?,
    @ColumnInfo(name = "release_date") override val releaseDate: String?,
    @ColumnInfo(name = "overview") override val overview: String?,
    @ColumnInfo(name = "tagline") override val tagline: String?,
    @ColumnInfo(name = "poster_path") override val posterPath: String?,
)
    : BaseEntity(rowid, movieId, title, popularity, releaseDate, overview, tagline, posterPath)