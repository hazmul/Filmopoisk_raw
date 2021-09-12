package com.ashush.filmopoisk_raw.presentation.nav_items.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.utils.getYear
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {
    val requestResult = MutableLiveData<DataMoviesModel>()
    val requestError = MutableLiveData<String>()

    val movies = MediatorLiveData<List<DataMoviesModel.Movie>>()
    var filter = SearchFilter()
        private set

    init {
        movies.addSource(requestResult) { applyFilter(filter) }
    }

    fun doRequest(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.getSearchResult(query = query)
            when {
                result.isSuccessful -> requestResult.postValue(result.body())
                else -> requestError.postValue(result.message())
            }
        }
    }

    fun applyFilter(filter: SearchFilter) {
        this.filter = filter
        movies.value = (requestResult.value?.movies?.filterNotNull() ?: emptyList())
            .filter { isMovieProper(it, filter) }
    }

    private fun isMovieProper(movie: DataMoviesModel.Movie, filter: SearchFilter): Boolean {
        return movie.adult == filter.isAdult
                && (if (filter.genres.isNotEmpty()) movie.genreIds?.any { genreId -> genreId in filter.genres }
            ?: false else true)
                && getYear(movie.releaseDate) >= filter.dateFrom
                && getYear(movie.releaseDate) <= filter.dateTo
                && movie.voteAverage ?: 0.0 >= filter.minVoteAverage
                && movie.voteAverage ?: 0.0 <= filter.maxVoteAverage
    }

}

