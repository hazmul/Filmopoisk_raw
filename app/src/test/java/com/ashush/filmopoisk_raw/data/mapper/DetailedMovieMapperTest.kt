package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.testmodels.DataConfigurationModelExample
import com.ashush.filmopoisk_raw.testmodels.DataDetailedMoviesExamples
import com.ashush.filmopoisk_raw.testmodels.DataGenresInfoExample
import com.ashush.filmopoisk_raw.testmodels.DomainDetailedMovieExample
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailedMovieMapperTest {

    @Before
    fun init() {
        DataConfig.genres = DataGenresInfoExample.value
        DataConfig.config = DataConfigurationModelExample.value
    }

    @Test
    fun mapToDomainDetailedMovieCase1() {
        val expectedResult = DomainDetailedMovieExample.domainDetailMovie1
        val actualResult = DetailedMovieMapper.mapToDomainDetailedMovie(DataDetailedMoviesExamples.dataDetailMovie1)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun mapToDomainDetailedMovieCase2() {
        val expectedResult = DomainDetailedMovieExample.domainDetailMovie2
        val actualResult = DetailedMovieMapper.mapToDomainDetailedMovie(DataDetailedMoviesExamples.dataDetailMovie2)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun mapToDomainDetailedMovieCase3() {
        val expectedResult = DomainDetailedMovieExample.domainDetailMovie3
        val actualResult = DetailedMovieMapper.mapToDomainDetailedMovie(DataDetailedMoviesExamples.dataDetailMovie3)
        Assert.assertEquals(expectedResult, actualResult)
    }
}
