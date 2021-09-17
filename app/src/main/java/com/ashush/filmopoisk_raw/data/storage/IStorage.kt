package com.ashush.filmopoisk_raw.data.storage

import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.domain.models.AppConfig

/**
 * Интрефес для прямой работы с локальными хранилищами
 */

interface IStorage {
    /**
     * Сохранить настройки API в хранилище
     * @param config настройки для сохранения
     * @return если успешно - true, если не успешно false
     */
    fun storeRemoteConfiguration(config: DataConfigurationModel): Boolean

    /**
     * Получить настройки API из хранилища
     * @return объект [DataConfigurationModel]
     */
    fun getRemoteConfiguration(): DataConfigurationModel?

    /**
     * Сохранить настройки приложения в хранилище
     * @param config настройки для сохранения
     * @return если успешно - true, если не успешно false
     */
    fun storeAppConfiguration(config: AppConfig): Boolean

    /**
     * Получить настройки приложения из хранилища
     * @return объект [AppConfig]
     */
    fun getAppConfiguration(): AppConfig

    /**
     * Получить объект DAO работающий с таблицей "Favorites"
     * @return объект [FavoritesDao]
     */
    fun getFavoriteDao(): FavoritesDao

    /**
     * Получить объект DAO работающий с таблицей "Watchlist"
     * @return объект [WatchlistDao]
     */
    fun getWatchlistDao(): WatchlistDao
}