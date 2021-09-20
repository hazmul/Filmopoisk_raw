package com.ashush.filmopoisk_raw.presentation.navitems.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.presentation.CoroutineTestRule
import com.ashush.filmopoisk_raw.testmodels.DomainMoviesExamples
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private val interactor: Interactor = mockk()
    private val filter: SearchFilter = SearchFilter()
    private var viewModel = SearchViewModel(interactor, CoroutineTestRule().testDispatcherProvider)

    @Before
    fun beforeTest() {
        viewModel = SearchViewModel(interactor, CoroutineTestRule().testDispatcherProvider)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun afterTest() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getMoviesAfterScrollDownSuccessCase1() {
        val initRequestResultLiveData = null
        viewModel.requestResult.value = initRequestResultLiveData

        coroutineTestRule.testDispatcher.runBlockingTest {
            val result = RequestResult.Success(data = DomainMoviesExamples.moviesModel1)
            coEvery { interactor.getSearchResult(query = any(), page = any()) } returns result

            val expectedRequestResultLiveData = DomainMoviesExamples.moviesModel1.moviesList

            viewModel.getMoviesAfterScrollDown()

            Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)

        }
    }

    @Test
    fun getMoviesAfterScrollDownSuccessCase2() {
        val initRequestResultLiveData = DomainMoviesExamples.moviesModel1.moviesList
        viewModel.requestResult.value = initRequestResultLiveData

        coroutineTestRule.testDispatcher.runBlockingTest {
            val result = RequestResult.Success(data = DomainMoviesExamples.moviesModel2)
            coEvery { interactor.getSearchResult(query = any(), page = any()) } returns result

            val expectedRequestResultLiveData = initRequestResultLiveData.toMutableList().apply {
                addAll(DomainMoviesExamples.moviesModel2.moviesList)
            }

            viewModel.getMoviesAfterScrollDown()

            Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)

        }
    }

    @Test
    fun getMoviesAfterScrollDownError() {
        val initRequestResultLiveData = null
        viewModel.requestResult.value = initRequestResultLiveData
        val initRequestErrorLiveData = null
        viewModel.requestError.value = initRequestErrorLiveData
        val errorMessage = "error"

        coroutineTestRule.testDispatcher.runBlockingTest {
            val result = RequestResult.Error<DomainMovies>(data = null, message = errorMessage)
            coEvery { interactor.getSearchResult(query = any(), page = any()) } returns result

            val expectedRequestResultLiveData = null

            viewModel.getMoviesAfterScrollDown()

            Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)
            Assert.assertEquals(errorMessage, viewModel.requestError.value)
        }
    }

    @Test
    fun getOtherMoviesSuccessCase1() {
        val initRequestResultLiveData = null
        viewModel.requestResult.value = initRequestResultLiveData
        val query = "some string123"

        coroutineTestRule.testDispatcher.runBlockingTest {
            val result = RequestResult.Success(data = DomainMoviesExamples.moviesModel1)
            coEvery { interactor.getSearchResult(query = query, page = any()) } returns result

            val expectedRequestResultLiveData = DomainMoviesExamples.moviesModel1.moviesList

            viewModel.getOtherMovies(query)

            Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)

        }
    }

    @Test
    fun getOtherMoviesSuccessCase2() {
        val initRequestResultLiveData = DomainMoviesExamples.moviesModel1.moviesList
        viewModel.requestResult.value = initRequestResultLiveData
        val query = "some string123"

        coroutineTestRule.testDispatcher.runBlockingTest {
            val result = RequestResult.Success(data = DomainMoviesExamples.moviesModel2)
            coEvery { interactor.getSearchResult(query = query, page = any()) } returns result

            val expectedRequestResultLiveData = DomainMoviesExamples.moviesModel2.moviesList

            viewModel.getOtherMovies(query)

            Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)

        }
    }

    @Test
    fun getOtherMoviesError() {
        val initRequestResultLiveData = null
        viewModel.requestResult.value = initRequestResultLiveData
        val initRequestErrorLiveData = null
        viewModel.requestError.value = initRequestErrorLiveData
        val errorMessage = "error"
        val query = "some string123"

        coroutineTestRule.testDispatcher.runBlockingTest {
            val result = RequestResult.Error<DomainMovies>(data = null, message = errorMessage)
            coEvery { interactor.getSearchResult(query = query, page = any()) } returns result

            val expectedRequestResultLiveData = emptyList<DomainMovies>()

            viewModel.getOtherMovies(query)

            Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)
            Assert.assertEquals(errorMessage, viewModel.requestError.value)
        }
    }

    @Test
    fun applyFilterCase1() {
        val initRequestResultLiveData = null
        viewModel.requestResult.value = initRequestResultLiveData
        val initFilteredMoviesMediatorLiveData = null
        viewModel.requestResult.value = initFilteredMoviesMediatorLiveData

        val expectedRequestResultLiveData = null
        val expectedFilteredMoviesMediatorLiveData = emptyList<DomainMovies>()

        viewModel.applyFilter(filter)

        Assert.assertEquals(filter, viewModel.filter)
        Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)
        Assert.assertEquals(expectedFilteredMoviesMediatorLiveData, viewModel.filteredMovies.value)

    }
    @Test
    fun applyFilterCase2() {
        val initRequestResultLiveData = DomainMoviesExamples.moviesModel2.moviesList
        viewModel.requestResult.value = initRequestResultLiveData
        val initFilteredMoviesMediatorLiveData = DomainMoviesExamples.moviesModel2.moviesList
        viewModel.requestResult.value = initFilteredMoviesMediatorLiveData


        val expectedRequestResultLiveData = DomainMoviesExamples.moviesModel2.moviesList
        val expectedFilteredMoviesMediatorLiveData = DomainMoviesExamples.moviesModel2.moviesList

        viewModel.applyFilter(filter)

        Assert.assertEquals(filter, viewModel.filter)
        Assert.assertEquals(expectedRequestResultLiveData, viewModel.requestResult.value)
        Assert.assertEquals(expectedFilteredMoviesMediatorLiveData, viewModel.filteredMovies.value)

    }
}