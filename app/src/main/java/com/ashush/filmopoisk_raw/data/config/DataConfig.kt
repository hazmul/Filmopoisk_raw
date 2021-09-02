package com.ashush.filmopoisk_raw.data.config

import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel

class DataConfig {
    companion object {
        const val API_KEY = "5f7614e8aead89a63d1dae413fd0153a"
        var baseURl = "https://api.themoviedb.org/"
        var config: DataConfigurationModel? = null
    }
}