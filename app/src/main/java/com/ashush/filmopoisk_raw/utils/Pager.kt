package com.ashush.filmopoisk_raw.utils

class Pager {
    var totalPages: Int = 2
    var currentPage: Int = 1
    fun hasNextPage(): Boolean {
        return currentPage < totalPages
    }
}