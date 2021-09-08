package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites

@Dao
abstract class FavoritesDao : BaseDao<Favorites>("favorites")
//{
//
//    private val tableName = "favorites"
//
//    @RawQuery
//    protected abstract fun getAll(query: SupportSQLiteQuery): List<Favorites>?
//
//    override fun getAll(): List<Favorites>? {
//        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName")
//        return getAll(query)
//    }
//
//    @RawQuery
//    protected abstract fun getById(query: SupportSQLiteQuery): Favorites?
//
//    override fun getById(movieId: Int): Favorites? {
//        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName WHERE movie_id LIKE $movieId LIMIT 1")
//        return getById(query)
//    }
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract override fun insert(moviesList: List<Favorites>)
//
//    @Delete
//    abstract override fun delete(movieList: List<Favorites>)
//
//    @Update
//    abstract override fun update(moviesList: List<Favorites>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract override fun insert(moviesList: Favorites)
//
//    @Delete
//    abstract override fun delete(movieList: Favorites)
//
//    @Update
//    abstract override fun update(moviesList: Favorites)
//}