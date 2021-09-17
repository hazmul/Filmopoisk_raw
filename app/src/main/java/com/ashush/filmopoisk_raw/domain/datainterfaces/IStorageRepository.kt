package com.ashush.filmopoisk_raw.domain.datainterfaces

import com.ashush.filmopoisk_raw.domain.models.AppConfig
import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.RequestResult

/**
 *
 * Интерфейс репозитория для работы с локальными хранилищами
 *
 */
interface IStorageRepository {

    /**
     * Сохранить настройки приложения в хранилище
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun storeAppConfiguration(config: AppConfig): RequestResult<Boolean>

    /**
     * Получить настройки приложения из хранилища
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getAppConfiguration(): RequestResult<AppConfig>

    /**
     * Получить все данные заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getAll(dataType: DataType): RequestResult<List<DomainDetailedMovie>>

    /**
     * Получить данные по id заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getById(movieId: Int, dataType: DataType): RequestResult<DomainDetailedMovie>

    /**
     * Сохранить объект [DomainDetailedMovie] в заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun insert(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Long>

    /**
     * Удалить объект [DomainDetailedMovie] в заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun delete(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Int>

    /**
     * Обновить объект [DomainDetailedMovie] в заданной категории при его наличии
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun updateMovie(movieDomain: DomainDetailedMovie, dataType: DataType): RequestResult<Int>
}