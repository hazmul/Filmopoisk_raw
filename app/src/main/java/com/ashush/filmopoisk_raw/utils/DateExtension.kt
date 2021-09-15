package com.ashush.filmopoisk_raw.utils


fun getYear(dateStr: String?): Int {
    val year = ("${"\\d{4}".toRegex().find(dateStr ?: "1")?.value}").toIntOrNull()
    return when {
        year == null -> 1
        year < 1 -> 1
        else -> year
    }
}