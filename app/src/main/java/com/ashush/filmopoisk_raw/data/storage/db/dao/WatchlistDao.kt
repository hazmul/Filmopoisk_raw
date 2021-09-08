package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist

@Dao
abstract class WatchlistDao : BaseDao<Watchlist>("watchlist")
//{
//
//    private val tableName = "watchlist"
//
//    @RawQuery
//    protected abstract fun getAll(query: SupportSQLiteQuery): List<Watchlist>?
//
//    override fun getAll(): List<Watchlist>? {
//        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName")
//        return getAll(query)
//    }
//
//    @RawQuery
//    protected abstract fun getById(query: SupportSQLiteQuery): Watchlist?
//
//    override fun getById(movieId: Int): Watchlist? {
//        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName WHERE movie_id LIKE $movieId LIMIT 1")
//        return getById(query)
//    }
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract override fun insert(moviesList: List<Watchlist>)
//
//    @Delete
//    abstract override fun delete(movieList: List<Watchlist>)
//
//    @Update
//    abstract override fun update(moviesList: List<Watchlist>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract override fun insert(moviesList: Watchlist)
//
//    @Delete
//    abstract override fun delete(movieList: Watchlist)
//
//    @Update
//    abstract override fun update(moviesList: Watchlist)
//}