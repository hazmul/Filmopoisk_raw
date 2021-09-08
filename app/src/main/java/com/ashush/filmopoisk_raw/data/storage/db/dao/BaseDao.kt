package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

abstract class BaseDao<T>(private val tableName: String) {

    @RawQuery
    protected abstract fun getAll(query: SupportSQLiteQuery): List<T>?

    fun getAll(): List<T>? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName")
        return getAll(query)
    }

    @RawQuery
    protected abstract fun getById(query: SupportSQLiteQuery): T?

    fun getById(movieId: Int): T? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName WHERE movie_id LIKE $movieId LIMIT 1")
        return getById(query)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(moviesList: List<T>)

    @Delete
    abstract fun delete(movieList: List<T>)

    @Update
    abstract fun update(moviesList: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(moviesList: T)

    @Delete
    abstract fun delete(movieList: T)

    @Update
    abstract fun update(moviesList: T)
}