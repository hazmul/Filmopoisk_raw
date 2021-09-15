package com.ashush.filmopoisk_raw.utils

import org.junit.Assert
import org.junit.Test

class DateExtensionKtTest {

    @Test
    fun getYearCase1() {
        val dateStr = "2021-11-30"
        val expectedResult = 2021
        val actualResult = getYear(dateStr)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getYearCase2() {
        val dateStr = "-3043-08-25"
        val expectedResult = 3043
        val actualResult = getYear(dateStr)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getYearCase3() {
        val dateStr = "02-02-02"
        val expectedResult = 1
        val actualResult = getYear(dateStr)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getYearCase4() {
        val dateStr = null
        val expectedResult = 1
        val actualResult = getYear(dateStr)
        Assert.assertEquals(expectedResult, actualResult)
    }
}
