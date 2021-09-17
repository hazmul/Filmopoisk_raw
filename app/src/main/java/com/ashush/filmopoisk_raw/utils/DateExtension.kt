package com.ashush.filmopoisk_raw.utils

/**
 * Функция занимающася поиском года в строке
 * @param dateStr строка содержащая некоторый набор цифр, например 12-23-1452.
 * @return в случае успеха найденный год в формате Int  или 1.
 */

fun getYear(dateStr: String?): Int {
    val year = ("${"\\d{4}".toRegex().find(dateStr ?: "1")?.value}").toIntOrNull()
    return when {
        year == null -> 1
        year < 1 -> 1
        else -> year
    }
}