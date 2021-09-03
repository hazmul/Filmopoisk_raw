package com.ashush.filmopoisk_raw.data.db.dao

import androidx.room.*
import com.ashush.filmopoisk_raw.data.db.entity.Favorites

@Dao
interface FavoritesDao {

    @Query("SELECT *, rowid FROM favorites")
    fun getAll(): List<Favorites>

    @Query("SELECT *, rowid FROM favorites WHERE movie_id IN (:moviesId)")
    fun loadAllByIds(moviesId: IntArray): List<Favorites>

    @Query("SELECT *, rowid FROM favorites WHERE movie_id LIKE :movieId LIMIT 1")
    fun findById(movieId: Int): Favorites

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg favorites: Favorites)

    @Delete
    fun delete(favorites: Favorites)

    @Update
    fun updateMovie(vararg favorites: Favorites)
}
