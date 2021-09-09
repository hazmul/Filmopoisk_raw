package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.config.DomainConfig
import com.ashush.filmopoisk_raw.domain.data_interfaces.IConfigRepository
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(private val storage: IStorage) : IConfigRepository {
    override fun saveConfiguration(config: DomainConfig) {
        storage.storeDomainConfiguration(config)
    }

    override fun getConfiguration(): DomainConfig {
        return storage.getDomainConfiguration()
    }
}