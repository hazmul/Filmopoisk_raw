package com.ashush.filmopoisk_raw.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashush.filmopoisk_raw.data.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.data.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.db.entity.Watchlist

@Database(entities = [Favorites::class, Watchlist::class], version = 1)
abstract class DBRoom : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    abstract fun watchlistDao(): WatchlistDao
}