package com.ashush.filmopoisk_raw.data.config

import com.ashush.filmopoisk_raw.data.config.DataConfig.Companion.API_KEY
import com.ashush.filmopoisk_raw.data.config.DataConfig.Companion.baseURl
import com.ashush.filmopoisk_raw.data.config.DataConfig.Companion.config
import com.ashush.filmopoisk_raw.data.config.DataConfig.Companion.genresInfo
import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.data.models.configuration.DataGenresInfo

/**
 *
 * В этом классе хранится информация для работы с внешим API
 *
 */

class DataConfig {
    companion object {

        /**
         *  @property API_KEY уникальный ключ для обращения к серверу.
         */
        const val API_KEY = "5f7614e8aead89a63d1dae413fd0153a"

        /**
         * @property baseURl содержит основную ссылку для обращения к серверу.
         */
        var baseURl = "https://api.themoviedb.org/"

        /**
         * @property config содержит информацию о настройках API. Некоторые запросы требуют данную информацию.
         */
        var config: DataConfigurationModel? = null

        /**
         * @property genresInfo содержит информацию о жанрах фильмов.
         */
        var genresInfo: DataGenresInfo? = null

        /**
         * Получет размер [size] желаемый для загрузки изображения.
         * @return возвращает первую часть ссылки для загрузки.
         */

        fun getBaseImageUrl(size: String? = null): String {
            return if (size != null) {
                "https://image.tmdb.org/t/p/$size"
            } else {
                "https://image.tmdb.org/t/p/original"
            }
        }
    }
}