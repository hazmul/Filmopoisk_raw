package com.ashush.filmopoisk_raw.presentation.nav_items.categories.topRated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import javax.inject.Inject

class TopRatedViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DataMoviesModel>()
    val requestError = MutableLiveData<String>()

    fun getMovies(): LiveData<PagingData<DataMoviesModel.Movie>> {
        return interactor.getMoviesTopRated().cachedIn(viewModelScope)
    }

}

