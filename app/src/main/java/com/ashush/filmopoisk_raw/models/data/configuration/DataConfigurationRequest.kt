package com.ashush.filmopoisk_raw.models.data.configuration


data class DataConfigurationRequest(
    val type: String = "GET",
    val api_key: String
)
