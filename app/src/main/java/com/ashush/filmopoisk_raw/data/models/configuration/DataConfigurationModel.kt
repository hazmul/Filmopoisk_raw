package com.ashush.filmopoisk_raw.data.models.configuration

import com.google.gson.annotations.SerializedName

/**
 * Модель данных получаемых с сервера при запросе настроек API
 */
data class DataConfigurationModel(
    @SerializedName("change_keys")
    val changeKeys: List<String>? = null,
    @SerializedName("images")
    val images: Images? = null
) {
    data class Images(
        @SerializedName("backdrop_sizes")
        val backdropSizes: List<String>? = null,
        @SerializedName("base_url")
        val baseUrl: String? = null,
        @SerializedName("logo_sizes")
        val logoSizes: List<String>? = null,
        @SerializedName("poster_sizes")
        val posterSizes: List<String>? = null,
        @SerializedName("profile_sizes")
        val profileSizes: List<String>? = null,
        @SerializedName("secure_base_url")
        val secureBaseUrl: String? = null,
        @SerializedName("still_sizes")
        val stillSizes: List<String>? = null
    )
}