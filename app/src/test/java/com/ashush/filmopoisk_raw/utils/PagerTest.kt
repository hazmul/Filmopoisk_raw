package com.ashush.filmopoisk_raw.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PagerTest {

    private var pager = Pager()

    @Before
    fun init() {
        pager = Pager()
    }

    @Test
    fun getNextPageCase1() {
        pager.currentPage = 2
        pager.totalPages = 3
        val expectedResult = 3
        val actualResult = pager.nextPage
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun hasNextPageCase1() {
        pager.currentPage = 1
        pager.totalPages = 2
        val expectedResult = true
        val actualResult = pager.hasNextPage()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun hasNextPageCase2() {
        pager.currentPage = 2
        pager.totalPages = 1
        val expectedResult = false
        val actualResult = pager.hasNextPage()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun hasNextPageCase3() {
        pager.currentPage = -1
        pager.totalPages = 3
        val expectedResult = true
        val actualResult = pager.hasNextPage()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun hasNextPageCase4() {
        pager.currentPage = 2
        pager.totalPages = -3
        val expectedResult = false
        val actualResult = pager.hasNextPage()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun resetPager() {
        pager.currentPage = 2
        pager.totalPages = 3
        val expectedResult = Pager()

        pager.resetPager()

        assertEquals(expectedResult.currentPage, pager.currentPage)
        assertEquals(expectedResult.totalPages, pager.totalPages)
    }
}