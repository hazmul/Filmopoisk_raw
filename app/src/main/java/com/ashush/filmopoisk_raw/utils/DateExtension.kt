package com.ashush.filmopoisk_raw.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


private const val DATE_PATTERN = "yyyy-MM-dd"

fun getYear(dateStr: String?): Int {
    val dateFormat: DateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
    val date = try { dateFormat.parse(dateStr.orEmpty()) } catch (e: Exception) { Calendar.getInstance().time }

    val calendar = Calendar.getInstance().apply {
        time = date!!
    }
    return calendar[Calendar.YEAR]
}