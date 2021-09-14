package com.ashush.filmopoisk_raw.presentation.navitems.categories.nowPlaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.Movies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.Pager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<List<Movies.Movie>>()
    val requestError = MutableLiveData<String>()

    private val pager = Pager()

    fun getMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (pager.hasNextPage()) {
                    when (val result = interactor.getMoviesNowPlaying(page = pager.currentPage)) {
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


}

