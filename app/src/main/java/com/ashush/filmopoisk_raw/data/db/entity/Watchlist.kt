package com.ashush.filmopoisk_raw.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity(tableName = "watchlist")
data class Watchlist(
    @PrimaryKey(autoGenerate = true)
    val rowid: Int,
    @ColumnInfo(name = "movie_id") val movieId: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "popularity") val popularity: Double?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "tagline") val tagline: String?,
)