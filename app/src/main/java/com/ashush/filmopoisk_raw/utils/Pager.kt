package com.ashush.filmopoisk_raw.utils

class Pager {
    var totalPages: Int = 1
    var currentPage: Int = 0
    val nextPage get() = currentPage + 1
    fun hasNextPage(): Boolean {
        return currentPage < totalPages
    }
    fun resetPager() {
        totalPages = 1
        currentPage = 0
    }
}