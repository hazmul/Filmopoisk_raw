package com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter.adapter

import com.ashush.filmopoisk_raw.data.models.configuration.DataGenresInfo.Genre

/**
 * Класс объектов для использования в диалоговом окне при задании фильтров
 */

data class GenreItem (
    val genre: Genre?,
    val isChecked: Boolean
)