package com.ashush.filmopoisk_raw.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashush.filmopoisk_raw.data.storage.db.dao.BaseDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist

@Database(entities = [Favorites::class, Watchlist::class], version = 1)
abstract class DBRoom : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    abstract fun watchlistDao(): WatchlistDao

    abstract fun <T> baseDao() : BaseDao<BaseEntity>
}