package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.mapper.DetailedMovieMapper
import com.ashush.filmopoisk_raw.data.mapper.MoviesMapper
import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.data.models.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.data.models.movies.DataDetailedMovie
import com.ashush.filmopoisk_raw.data.models.movies.DataMovies
import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.testmodels.*
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
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
        DataConfig.config = DataConfigurationModelExample.value
        DataConfig.genresInfo = DataGenresInfoExample.value
    }

    @Test
    fun getConfigurationSuccess() = runBlocking {
        DataConfig.config = null
        DataConfig.genresInfo = null

        val response = Response.success(DataConfigurationModelExample.value)

        coEvery { retrofitServiceProvider.getService().getConfiguration() } returns response
        coEvery { storage.storeRemoteConfiguration(response.body()!!) } returns true

        val expectedResult = RequestResult.Success(true)
        val actualResult = dataRepository.getConfiguration()

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getConfiguration()
            storage.storeRemoteConfiguration(response.body()!!)
            DataConfig.config = response.body()!!
        }
    }

    @Test
    fun getConfigurationError() = runBlocking {
        DataConfig.config = null
        DataConfig.genresInfo = null

        val response = Response.error<DataConfigurationModel>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"

        coEvery { retrofitServiceProvider.getService().getConfiguration() } returns response
        coEvery { storage.getRemoteConfiguration() } returns DataConfigurationModelExample.value

        val expectedResult = RequestResult.Error(false, message)
        val actualResult = dataRepository.getConfiguration()

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getConfiguration()
            storage.getRemoteConfiguration()
            DataConfig.config = DataConfigurationModelExample.value
        }
    }

    @Test
    fun getGenresInfoSuccess() = runBlocking {
        DataConfig.config = null
        DataConfig.genresInfo = null

        val response = Response.success(DataGenresInfoExample.value)

        coEvery { retrofitServiceProvider.getService().getGenresInfo() } returns response

        val expectedResult = RequestResult.Success(true)
        val actualResult = dataRepository.getGenresInfo()

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getGenresInfo()
            DataConfig.genresInfo = response.body()!!
        }
    }

    @Test
    fun getGenresInfoError() = runBlocking {
        DataConfig.config = null
        DataConfig.genresInfo = null

        val response = Response.error<DataGenresInfo>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"

        coEvery { retrofitServiceProvider.getService().getGenresInfo() } returns response

        val expectedResult = RequestResult.Error(false, message)
        val actualResult = dataRepository.getGenresInfo()

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getGenresInfo()
        }
    }

    @Test
    fun getMovieDetailSuccess() = runBlocking {
        val response = Response.success(DataDetailedMoviesExamples.dataDetailMovie1)
        val movieId = 4
        val language = "ru-RU"
        val appendToResponse = "some additional info"
        val successResult = DomainDetailedMovieExample.domainDetailMovie1

        coEvery {
            retrofitServiceProvider.getService()
                .getMovieDetail(
                    movieId = movieId,
                    language = language,
                    appendToResponse = appendToResponse
                )
        } returns response

        val expectedResult = RequestResult.Success(successResult)
        val actualResult = dataRepository.getMovieDetail(
            movieId = movieId,
            language = language,
            appendToResponse = appendToResponse
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService()
                .getMovieDetail(
                    movieId = movieId,
                    language = language,
                    appendToResponse = appendToResponse
                )
            DetailedMovieMapper.mapToDomainDetailedMovie(DataDetailedMoviesExamples.dataDetailMovie1)
        }
    }

    @Test
    fun getMovieDetailError() = runBlocking {
        val response = Response.error<DataDetailedMovie>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"
        val movieId = 4
        val language = "ru-RU"
        val appendToResponse = "some additional info"

        coEvery {
            retrofitServiceProvider.getService()
                .getMovieDetail(
                    movieId = movieId,
                    language = language,
                    appendToResponse = appendToResponse
                )
        } returns response

        val expectedResult = RequestResult.Error(null, message)
        val actualResult = dataRepository.getMovieDetail(
            movieId = movieId,
            language = language,
            appendToResponse = appendToResponse
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService()
                .getMovieDetail(
                    movieId = movieId,
                    language = language,
                    appendToResponse = appendToResponse
                )
        }
    }

    @Test
    fun getMoviesPopularSuccess() = runBlocking {
        val response = Response.success(DataMoviesExamples.dataMoviesModel1)
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"
        val successResult = DomainMoviesExamples.moviesModel1

        coEvery {
            retrofitServiceProvider.getService().getMoviesPopular(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Success(successResult)
        val actualResult = dataRepository.getMoviesPopular(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesPopular(
                language = language,
                page = page,
                region = region
            )
            MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel1)
        }
    }

    @Test
    fun getMoviesPopularError() = runBlocking {
        val response = Response.error<DataMovies>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"

        coEvery {
            retrofitServiceProvider.getService().getMoviesPopular(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Error(null, message)
        val actualResult = dataRepository.getMoviesPopular(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesPopular(
                language = language,
                page = page,
                region = region
            )
        }
    }

    @Test
    fun getMoviesTopRatedSuccess() = runBlocking {
        val response = Response.success(DataMoviesExamples.dataMoviesModel2)
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"
        val successResult = DomainMoviesExamples.moviesModel2

        coEvery {
            retrofitServiceProvider.getService().getMoviesTopRated(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Success(successResult)
        val actualResult = dataRepository.getMoviesTopRated(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesTopRated(
                language = language,
                page = page,
                region = region
            )
            MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel2)
        }
    }

    @Test
    fun getMoviesTopRatedError() = runBlocking {
        val response = Response.error<DataMovies>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"

        coEvery {
            retrofitServiceProvider.getService().getMoviesTopRated(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Error(null, message)
        val actualResult = dataRepository.getMoviesTopRated(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesTopRated(
                language = language,
                page = page,
                region = region
            )
        }
    }

    @Test
    fun getMoviesUpcomingSuccess() = runBlocking {
        val response = Response.success(DataMoviesExamples.dataMoviesModel3)
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"
        val successResult = DomainMoviesExamples.moviesModel3


        coEvery {
            retrofitServiceProvider.getService().getMoviesUpcoming(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Success(successResult)
        val actualResult = dataRepository.getMoviesUpcoming(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesUpcoming(
                language = language,
                page = page,
                region = region
            )
            MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel3)
        }
    }

    @Test
    fun getMoviesUpcomingError() = runBlocking {
        val response = Response.error<DataMovies>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"


        coEvery {
            retrofitServiceProvider.getService().getMoviesUpcoming(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Error(null, message)
        val actualResult = dataRepository.getMoviesUpcoming(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesUpcoming(
                language = language,
                page = page,
                region = region
            )
        }
    }

    @Test
    fun getMoviesNowPlayingSuccess() = runBlocking {
        val response = Response.success(DataMoviesExamples.dataMoviesModel1)
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"
        val successResult = DomainMoviesExamples.moviesModel1

        coEvery {
            retrofitServiceProvider.getService().getMoviesNowPlaying(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Success(successResult)
        val actualResult = dataRepository.getMoviesNowPlaying(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesNowPlaying(
                language = language,
                page = page,
                region = region
            )
            MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel1)
        }
    }

    @Test
    fun getMoviesNowPlayingError() = runBlocking {
        val response = Response.error<DataMovies>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"
        val page = 4
        val language = "ru-RU"
        val region = "en-EN"

        coEvery {
            retrofitServiceProvider.getService().getMoviesNowPlaying(
                language = language,
                page = page,
                region = region
            )
        } returns response

        val expectedResult = RequestResult.Error(null, message)
        val actualResult = dataRepository.getMoviesNowPlaying(
            language = language,
            page = page,
            region = region
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getMoviesNowPlaying(
                language = language,
                page = page,
                region = region
            )
        }
    }

    @Test
    fun getSearchResultSuccess() = runBlocking {
        val response = Response.success(DataMoviesExamples.dataMoviesModel3)
        val language = "ru-RU"
        val query = "some interesting movie"
        val page = 32
        val includeAdult = true
        val region = "en-EN"
        val year = 5123
        val primaryReleaseYear = null
        val successResult = DomainMoviesExamples.moviesModel3

        coEvery {
            retrofitServiceProvider.getService().getSearchResult(
                language = language,
                query = query,
                page = page,
                includeAdult = includeAdult,
                region = region,
                year = year,
                primaryReleaseYear = primaryReleaseYear,
            )
        } returns response

        val expectedResult = RequestResult.Success(successResult)
        val actualResult = dataRepository.getSearchResult(
            language = language,
            query = query,
            page = page,
            includeAdult = includeAdult,
            region = region,
            year = year,
            primaryReleaseYear = primaryReleaseYear,
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getSearchResult(
                language = language,
                query = query,
                page = page,
                includeAdult = includeAdult,
                region = region,
                year = year,
                primaryReleaseYear = primaryReleaseYear,
            )
            MoviesMapper.mapToDomainMovies(DataMoviesExamples.dataMoviesModel3)
        }
    }

    @Test
    fun getSearchResultError() = runBlocking {
        val response = Response.error<DataMovies>(
            400,
            "{\"key\":[\"somestuff\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        val message = "Response.error()"
        val language = "ru-RU"
        val query = "some interesting movie"
        val page = 32
        val includeAdult = true
        val region = "en-EN"
        val year = 5123
        val primaryReleaseYear = null

        coEvery {
            retrofitServiceProvider.getService().getSearchResult(
                language = language,
                query = query,
                page = page,
                includeAdult = includeAdult,
                region = region,
                year = year,
                primaryReleaseYear = primaryReleaseYear,
            )
        } returns response

        val expectedResult = RequestResult.Error(null, message)
        val actualResult = dataRepository.getSearchResult(
            language = language,
            query = query,
            page = page,
            includeAdult = includeAdult,
            region = region,
            year = year,
            primaryReleaseYear = primaryReleaseYear,
        )

        Assert.assertEquals(expectedResult.data, actualResult.data)
        Assert.assertEquals(expectedResult.message, actualResult.message)

        coVerifySequence {
            retrofitServiceProvider.getService().getSearchResult(
                language = language,
                query = query,
                page = page,
                includeAdult = includeAdult,
                region = region,
                year = year,
                primaryReleaseYear = primaryReleaseYear,
            )
        }
    }
}
