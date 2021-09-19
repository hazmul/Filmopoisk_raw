package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.testmodels.DataConfigurationModelExample
import com.ashush.filmopoisk_raw.testmodels.DataGenresInfoExample
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class DataRepositoryImplTest {

    private val storage: IStorage = mockk()
    private val retrofitServiceProvider: RetrofitServiceProvider = mockk()
    private val dataRepository = RemoteRepositoryImpl(retrofitServiceProvider, storage)

    @Before
    fun init() {
        DataConfig.config = null
        DataConfig.genresInfo = null
    }

    @Test
    fun getConfiguration() = runBlocking {

        val response = Response.success(DataConfigurationModelExample.value)

        coEvery { retrofitServiceProvider.getService().getConfiguration() } returns response
        coEvery { storage.storeRemoteConfiguration(response.body()!!) } returns true

        val expectedResult = RequestResult.Success(true)
        val actualResult = dataRepository.getConfiguration()

        Assert.assertEquals(expectedResult.data, actualResult.data)

        coVerifySequence {
            retrofitServiceProvider.getService().getConfiguration()
            storage.storeRemoteConfiguration(response.body()!!)
            DataConfig.config = response.body()!!
        }
    }

    @Test
    fun getGenresInfo() = runBlocking {

        val response = Response.success(DataGenresInfoExample.value)

        coEvery { retrofitServiceProvider.getService().getGenresInfo() } returns response

        val expectedResult = RequestResult.Success(true)
        val actualResult = dataRepository.getGenresInfo()

        Assert.assertEquals(expectedResult.data, actualResult.data)

        coVerifySequence {
            retrofitServiceProvider.getService().getGenresInfo()
            DataConfig.genresInfo = response.body()!!
        }
    }

    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getMoviesPopular() {
    }

    @Test
    fun getMoviesTopRated() {
    }

    @Test
    fun getMoviesUpcoming() {
    }

    @Test
    fun getMoviesNowPlaying() {
    }

    @Test
    fun getSearchResult() {
    }
}