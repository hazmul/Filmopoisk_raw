package com.ashush.filmopoisk_raw.domain.models

class AppConfig(
    var downloadImageAllowed: Boolean = true,
    var cacheImageAllowed: Boolean = true,
    var themeType: Boolean = false,
    var enableNotification: Boolean = true,
    var viewType: ViewType = ViewType.GRIDVIEW,
) {

    companion object {

        @Volatile
        private var instance: AppConfig? = null

        fun getInstance(): AppConfig? {
            return instance
        }

        fun setInstance(config: AppConfig) {
            instance = config
        }
    }


    enum class ViewType { GRIDVIEW, LISTVIEW }
}



