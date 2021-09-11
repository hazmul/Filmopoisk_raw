package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

abstract class BaseDao<T>(private val tableName: String) {

    @RawQuery
    protected abstract suspend fun getAll(query: SupportSQLiteQuery): List<T>?

    suspend fun getAll(): List<T>? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName")
        return getAll(query)
    }

    @RawQuery
    protected abstract suspend fun getById(query: SupportSQLiteQuery): T?

    suspend fun getById(movieId: Int): T? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName WHERE movie_id LIKE $movieId LIMIT 1")
        return getById(query)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(moviesList: List<T>): List<Long>

    @Delete
    abstract suspend fun delete(movieList: List<T>): Int

    @Update
    abstract suspend fun update(moviesList: List<T>): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(moviesList: T): Long

    @Delete
    abstract suspend fun delete(movieList: T): Int

    @Update
    abstract suspend fun update(moviesList: T): Int
}

