package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.testmodels.DataConfigurationModelExample
import com.ashush.filmopoisk_raw.testmodels.DataGenresInfoExample
import com.ashush.filmopoisk_raw.testmodels.DataMoviesExamples
import com.ashush.filmopoisk_raw.testmodels.DomainMoviesExamples
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesMapperTest {

    private val moviesMapper = MoviesMapper()

    @Before
    fun init() {
        DataConfig.genres = DataGenresInfoExample.value
        DataConfig.config = DataConfigurationModelExample.value
    }

    @Test
    fun mapToDomainMoviesCase1() {
        val expectedResult = DomainMoviesExamples.moviesModel1
        val actualResult = MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel1)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun mapToDomainMoviesCase2() {
        val expectedResult = DomainMoviesExamples.moviesModel2
        val actualResult = MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel2)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun mapToDomainMoviesCase3() {
        val expectedResult = DomainMoviesExamples.moviesModel3
        val actualResult = MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel3)
        Assert.assertEquals(expectedResult, actualResult)
    }
}
