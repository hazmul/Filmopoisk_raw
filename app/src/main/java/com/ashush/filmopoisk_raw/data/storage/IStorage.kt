package com.ashush.filmopoisk_raw.data.storage

import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.domain.config.DomainConfig
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel

interface IStorage {

    fun storeRemoteConfiguration(config: DataConfigurationModel)
    fun getRemoteConfiguration(): DataConfigurationModel?

    fun storeDomainConfiguration(config: DomainConfig)
    fun getDomainConfiguration(): DomainConfig

    fun getFavoriteDao(): FavoritesDao
    fun getWatchlistDao(): WatchlistDao
}