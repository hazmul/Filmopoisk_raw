package com.ashush.filmopoisk_raw.presentation.navitems.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.DispatcherProvider
import com.ashush.filmopoisk_raw.utils.IDispatcherProvider
import com.ashush.filmopoisk_raw.utils.Pager
import com.ashush.filmopoisk_raw.utils.getYear
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * ViewModel экрана приложения c поиском фильмов.
 * @param interactor интерактор для получения данных
 * @param dispatcherProvider провайдер CoroutineDispatcher для выбора потока выполнения
 * @property requestResult LiveData содержит информацию об фильме полученного по результатам запроса.
 * @property requestError LiveData содержит информацию об ошибке по результатам запроса.
 * @property pager обработчик страниц
 * @property viewModelJob объект отражающий состояние работы в рамках которых выполняются корутины в этой ViewModel.
 * @property lastQuery последняя запрошенная информация.
 * @property filteredMovies MediatorLiveData содержит список отфильтрованных фильмов.
 * @property filter заданный пользователем фильтр.
 */

class SearchViewModel @Inject constructor(
    private val interactor: Interactor,
    private val dispatcherProvider: IDispatcherProvider
) : ViewModel() {

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    val requestResult = MutableLiveData<List<DomainMovies.Movie>>()
    val requestError = MutableLiveData<String>()
    val filteredMovies = MediatorLiveData<List<DomainMovies.Movie>>()

    private var pager = Pager()
    private var viewModelJob = SupervisorJob()
    private var lastQuery = ""
    var filter = SearchFilter()
        private set

    init {
        filteredMovies.addSource(requestResult) { applyFilter(filter) }
    }

    /**
     * Подгрузить допольнительный список фильмов для продолжения скролла
     */
    fun getMoviesAfterScrollDown() {
        getMovies(lastQuery)
    }

    /**
     * Подгрузить новый список фильмов на основании введенной строки [query].
     * Сбрасывает [pager] и LiveData [requestResult], MediatorLiveData [filteredMovies] т.к. запрос на новый список фильмов.
     */
    fun getOtherMovies(query: String) {
        pager.resetPager()
        requestResult.value = emptyList()
        filteredMovies.value = emptyList()
        getMovies(query)
    }

    private fun getMovies(query: String) {
        viewModelJob.cancelChildren()
        viewModelScope.launch {
            withContext(dispatcherProvider.io + viewModelJob) {
                if (pager.hasNextPage()) {
                    lastQuery = query
                    when (val result = interactor.getSearchResult(query = query, page = pager.nextPage)) {
                        is RequestResult.Success -> {
                            pager.totalPages = result.data!!.totalPages
                            pager.currentPage = result.data.currentPage
                            if (requestResult.value == null) {
                                requestResult.postValue(result.data.moviesList)
                            } else {
                                val newData = requestResult.value!!.toMutableList()
                                newData.addAll(result.data.moviesList)
                                requestResult.postValue(newData)
                            }
                        }
                        is RequestResult.Error -> requestError.postValue(result.message!!)
                    }
                }
            }
        }
    }

    /**
     * Применить новый фильтр и обновить MediatorLiveData [filteredMovies] на валидность фильмов.
     */
    fun applyFilter(filter: SearchFilter) {
        this.filter = filter
        filteredMovies.value = (requestResult.value ?: emptyList())
            .filter { isMovieProper(it, filter) }
    }

    private fun isMovieProper(movie: DomainMovies.Movie, filter: SearchFilter): Boolean {
        return movie.adult == filter.isAdult
                && (if (filter.genres.isNotEmpty()) movie.genreIds.any { genreId -> genreId in filter.genres }
        else true)
                && getYear(movie.releaseDate) >= filter.dateFrom
                && getYear(movie.releaseDate) <= filter.dateTo
                && movie.voteAverage >= filter.minVoteAverage
                && movie.voteAverage <= filter.maxVoteAverage
    }

}



