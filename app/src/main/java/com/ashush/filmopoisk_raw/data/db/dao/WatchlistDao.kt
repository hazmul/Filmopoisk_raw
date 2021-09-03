package com.ashush.filmopoisk_raw.data.db.dao

import androidx.room.*
import com.ashush.filmopoisk_raw.data.db.entity.Watchlist

@Dao
interface WatchlistDao {

    @Query("SELECT *, rowid FROM watchlist")
    fun getAll(): List<Watchlist>

    @Query("SELECT *, rowid FROM watchlist WHERE movie_id IN (:moviesId)")
    fun loadAllByIds(moviesId: IntArray): List<Watchlist>

    @Query("SELECT *, rowid FROM watchlist WHERE movie_id LIKE :movieId LIMIT 1")
    fun findById(movieId: Int): Watchlist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg watchlist: Watchlist)

    @Delete
    fun delete(watchlist: Watchlist)

    @Update
    fun updateMovie(vararg watchlist: Watchlist)
}
