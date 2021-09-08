package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity

@Dao
abstract class BaseDao<T : BaseEntity>(private val tableName: String) {

    @RawQuery
    protected abstract fun getAll(query: SupportSQLiteQuery): List<T>?

    fun getAll(): List<T>? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName")
        return getAll(query)
    }

    @RawQuery
    protected abstract fun getAllByIds(query: SupportSQLiteQuery): List<T>?

    fun getAllByIds(moviesId: List<Int>): List<T>? {
        val result = StringBuilder()
        for (index in moviesId.indices) {
            if (index != 0) {
                result.append(",")
            }
            result.append("'").append(moviesId[index]).append("'")
        }
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE id IN ($result);")
        return getAllByIds(query)
    }

    @RawQuery
    protected abstract fun getById(query: SupportSQLiteQuery): T?

    fun getById(movieId: Int): T? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName WHERE movie_id LIKE $movieId LIMIT 1")
        return getById(query)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(moviesList: List<T>)

    @Delete
    abstract fun delete(movieList: T)

    @Update
    abstract fun updateMovies(moviesList: List<T>)
}