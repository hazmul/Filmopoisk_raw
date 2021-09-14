package com.ashush.filmopoisk_raw.presentation.navitems.search

import java.util.*

data class SearchFilter (
    var isAdult: Boolean? = false,
    var genres: List<Int> = emptyList(),
    var dateFrom: Int = 0,
    var dateTo: Int = Calendar.getInstance().get(Calendar.YEAR),
    var minVoteAverage: Double = 0.0,
    var maxVoteAverage: Double = 10.0
)