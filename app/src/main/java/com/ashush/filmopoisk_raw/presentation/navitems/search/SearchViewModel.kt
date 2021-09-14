package com.ashush.filmopoisk_raw.presentation.navitems.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.Movies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.Pager
import com.ashush.filmopoisk_raw.utils.getYear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<List<Movies.Movie>>()
    val requestError = MutableLiveData<String>()

    private var pager = Pager()

    private var lastQuery = ""

    val filteredMovies = MediatorLiveData<List<Movies.Movie>>()
    var filter = SearchFilter()
        private set

    init {
        filteredMovies.addSource(requestResult) { applyFilter(filter) }
    }

    fun getMoviesAfterScrollDown() {
        getMovies(lastQuery)
    }

    fun getOtherMovies(query: String) {
        pager = Pager()
        requestResult.value = emptyList()
        filteredMovies.value = emptyList()
        getMovies(query)
    }

    fun getMovies(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (pager.hasNextPage()) {
                    lastQuery = query
                    when (val result = interactor.getSearchResult(query = query, page = pager.currentPage)) {
                        is RequestResult.Success -> {
                            if (requestResult.value == null) {
                                requestResult.postValue(result.data!!.moviesList)
                            } else {
                                val newData = requestResult.value!!.toMutableList()
                                newData.addAll(result.data!!.moviesList)
                                requestResult.postValue(newData)
                            }
                            pager.totalPages = result.data.totalPages
                            pager.currentPage = result.data.currentPage
                        }
                        is RequestResult.Error -> requestError.postValue(result.message!!)
                    }
                }
            }
        }
    }

    fun applyFilter(filter: SearchFilter) {
        this.filter = filter
        filteredMovies.value = (requestResult.value ?: emptyList())
            .filter { isMovieProper(it, filter) }
    }

    private fun isMovieProper(movie: Movies.Movie, filter: SearchFilter): Boolean {
        return movie.adult == filter.isAdult
                && (if (filter.genres.isNotEmpty()) movie.genresId.any { genreId -> genreId in filter.genres }
        else true)
                && getYear(movie.releaseDate) >= filter.dateFrom
                && getYear(movie.releaseDate) <= filter.dateTo
                && movie.voteAverage >= filter.minVoteAverage
                && movie.voteAverage <= filter.maxVoteAverage
    }

}



