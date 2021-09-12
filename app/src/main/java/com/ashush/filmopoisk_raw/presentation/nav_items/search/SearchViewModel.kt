package com.ashush.filmopoisk_raw.presentation.nav_items.search

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.filter
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.utils.getYear
import javax.inject.Inject

class SearchViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DataMoviesModel.Movie>()
    val requestError = MutableLiveData<String>()

    var filter = MutableLiveData<SearchFilter>()
    var lastQuery = MutableLiveData<String>()

    init {
        filter.value = SearchFilter()
    }

    fun getMovies(query: String): LiveData<PagingData<DataMoviesModel.Movie>> {
        lastQuery.value = query
            return interactor.getSearchResult(query = query)
                .map { it -> it.filter { isMovieProper(it, filter.value!!) } }
    }

    fun applyFilter(filter: SearchFilter) {
        this.filter.value = filter

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

