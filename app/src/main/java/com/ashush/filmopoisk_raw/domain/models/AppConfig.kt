package com.ashush.filmopoisk_raw.domain.models

/**
 * Класс содержащий в себе настройки приложения для легкого доступа к ним
 * @param downloadImageAllowed возможно ли загружать картинки? true/false
 * @param cacheImageAllowed возможно ли кэшировать картинки? true/false
 * @param themeType смена темы: true - темная/false - светлая
 * @param enableNotification  доступны ли обновления? true/false
 * @param viewType смена отображения в списках [ViewType]
 */

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

    /**
     * Класс перечисление возможных отображений в списках
     * @see GRIDVIEW в виде сетки
     * @see LISTVIEW в виде списка
     */
    enum class ViewType { GRIDVIEW, LISTVIEW }
}



