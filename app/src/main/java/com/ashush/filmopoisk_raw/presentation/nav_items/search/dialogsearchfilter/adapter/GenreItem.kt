package com.ashush.filmopoisk_raw.presentation.nav_items.search.dialogsearchfilter.adapter

import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo.Genre

data class GenreItem (
    val genre: Genre?,
    val isChecked: Boolean
)