package com.ashush.filmopoisk_raw.data.storage

import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel

interface IStorage {

    fun storeRemoteConfiguration(config: DataConfigurationModel)
    fun getRemoteConfiguration() : DataConfigurationModel?
}