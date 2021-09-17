package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

/**
 * Обобщенный класс для обращения к базе данных
 * @param T тип сохраненных таблиц
 */

abstract class BaseDao<T>(private val tableName: String) {

    /**
     * Вспомогательная функция для getAll(): List<T>?
     * Запрос на получение всех сохраненных объектов в таблице
     * @param query сформированный SQL запрос
     */

    @RawQuery
    protected abstract suspend fun getAll(query: SupportSQLiteQuery): List<T>?

    /**
     * Основная функция
     * Запрос на получение всех сохраненных объектов в таблице
     * Функция формирует запрос в зависимости от [tableName]
     */

    suspend fun getAll(): List<T>? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName")
        return getAll(query)
    }

    /**
     * Вспомогательная функция для getById(movieId: Int): T?
     * Запрос на получение  сохраненного объекта в таблице по id
     * @param query сформированный SQL запрос
     */

    @RawQuery
    protected abstract suspend fun getById(query: SupportSQLiteQuery): T?

    /**
     * Основная функция
     * Запрос на получение  сохраненного объекта в таблице по id
     * Функция формирует запрос в зависимости от [tableName]
     */
    suspend fun getById(movieId: Int): T? {
        val query = SimpleSQLiteQuery("SELECT *, rowid FROM $tableName WHERE movie_id LIKE $movieId LIMIT 1")
        return getById(query)
    }

    /**
     * Сохранение списка объектов в таблицу
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(moviesList: List<T>): List<Long>

    /**
     * Удаление списка объектов из таблицы
     */
    @Delete
    abstract suspend fun delete(movieList: List<T>): Int

    /**
     * Обновление списка объектов в таблице
     */
    @Update
    abstract suspend fun update(moviesList: List<T>): Int

    /**
     * Сохранение конкретного объекта в таблицу
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(moviesList: T): Long

    /**
     * Удаление конкретного объекта из таблицы
     */
    @Delete
    abstract suspend fun delete(movieList: T): Int

    /**
     * Обновление конкретного объекта в таблице
     */
    @Update
    abstract suspend fun update(moviesList: T): Int
}

