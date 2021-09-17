package com.ashush.filmopoisk_raw.utils

/**
 * Класс реализующий отслеживание возможности запросить следующий страницу
 * @property totalPages всего доступно страниц
 * @property currentPage текущая страница
 * @property nextPage - геттер инкремент для текущей страницы
 */

class Pager {
    var totalPages: Int = 1
    var currentPage: Int = 0
    val nextPage get() = currentPage + 1

    /**
     * @return возможность запросить следующий страницу
     */

    fun hasNextPage(): Boolean {
        return currentPage < totalPages
    }

    /**
     * Сбрасывает информацию о страницах в начальные значения
     */

    fun resetPager() {
        totalPages = 1
        currentPage = 0
    }
}