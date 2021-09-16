package com.ashush.filmopoisk_raw.data.remote

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit

@RunWith(MockitoJUnitRunner::class)
class RetrofitServiceProviderTest {

    private val retrofit = mockk<Retrofit>()
    private val retrofitServiceProvider = RetrofitServiceProvider(retrofit)
    private val retrofitService = mockk<RetrofitService>()

    @Test
    fun getService() {

        every { retrofit.create(RetrofitService::class.java) } returns retrofitService

        val expectedResult = retrofitService
        val actualResult = retrofitServiceProvider.getService()

        Assert.assertEquals(expectedResult, actualResult)
        verify(exactly = 1) { retrofit.create(RetrofitService::class.java) }
    }
}