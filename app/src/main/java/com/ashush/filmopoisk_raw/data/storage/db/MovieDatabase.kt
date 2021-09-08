package com.ashush.filmopoisk_raw.data.storage.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist

@Database(entities = [Watchlist::class, Favorites::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "MOVIE_DB"

        @Volatile
        private var instance: MovieDatabase? = null

        fun getInstance(applicationContext: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                val result = Room.databaseBuilder(
                    applicationContext,
                    MovieDatabase::class.java,
                    DB_NAME
                ).build()
                instance = result
                return result
            }
        }
    }

    abstract fun favoritesDao(): FavoritesDao

    abstract fun watchlistDao(): WatchlistDao

}
