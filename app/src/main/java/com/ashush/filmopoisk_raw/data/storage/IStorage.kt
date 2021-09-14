package com.ashush.filmopoisk_raw.data.storage

import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel

interface IStorage {

    fun storeRemoteConfiguration(config: DataConfigurationModel)
    fun getRemoteConfiguration(): DataConfigurationModel?

    fun getFavoriteDao(): FavoritesDao
    fun getWatchlistDao(): WatchlistDao
}