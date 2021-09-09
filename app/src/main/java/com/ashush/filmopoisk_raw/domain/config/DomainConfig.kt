package com.ashush.filmopoisk_raw.domain.config

import com.ashush.filmopoisk_raw.domain.data_interfaces.IConfigRepository

class DomainConfig(
    val downloadImageAllowed: Boolean,
    val cacheImageAllowed: Boolean,
    val themeType: Boolean,
    val enableNotification: Boolean,
    val recyclerViewType: Boolean
) {

    companion object {
        @Volatile
        private var instance: DomainConfig? = null

        fun getInstance(configRepository: IConfigRepository): DomainConfig {
            return instance ?: synchronized(this) {
                val result = configRepository.getConfiguration()
                instance = result
                return result
            }
        }
    }

//                            true     false
//    enum class ViewType { GRIDVIEW, LISTVIEW }
//    enum class ThemeType { DARK, LIGHT }
}