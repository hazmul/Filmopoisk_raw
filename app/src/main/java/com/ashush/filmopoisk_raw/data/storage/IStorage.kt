package com.ashush.filmopoisk_raw.data.storage

import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.domain.models.AppConfig

interface IStorage {

    fun storeRemoteConfiguration(config: DataConfigurationModel): Boolean
    fun getRemoteConfiguration(): DataConfigurationModel?

    fun storeAppConfiguration(config: AppConfig): Boolean
    fun getAppConfiguration(): AppConfig

    fun getFavoriteDao(): FavoritesDao
    fun getWatchlistDao(): WatchlistDao
}