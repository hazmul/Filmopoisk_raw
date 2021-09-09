package com.ashush.filmopoisk_raw.domain.data_interfaces

import com.ashush.filmopoisk_raw.domain.config.DomainConfig

interface IConfigRepository {
    fun saveConfiguration(config: DomainConfig)
    fun getConfiguration(): DomainConfig
}