package com.ashush.filmopoisk_raw.domain.models

object DomainConfig {
    @Volatile
    private var instance: DomainConfig? = null

    fun getInstance(): DomainConfig {
        return instance ?: synchronized(this) {
            val result = this
            instance = result
            return result
        }
    }

    var downloadImageAllowed: Boolean = true
    var cacheImageAllowed: Boolean = true
    var themeType: Boolean = false
    var enableNotification: Boolean = true
    var recyclerViewType: ViewType = ViewType.GRIDVIEW

    enum class ViewType { GRIDVIEW, LISTVIEW }
}