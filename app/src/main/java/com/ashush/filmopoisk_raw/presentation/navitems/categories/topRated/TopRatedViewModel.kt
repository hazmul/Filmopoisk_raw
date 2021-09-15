package com.ashush.filmopoisk_raw.presentation.navitems.categories.topRated

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.Movies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.Pager
import kotlinx.coroutines.*
import javax.inject.Inject

class TopRatedViewModel @Inject constructor(
    private var interactor: Interactor
) : ViewModel() {

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    val requestResult = MutableLiveData<List<Movies.Movie>>()
    val requestError = MutableLiveData<String>()

    private val pager = Pager()
    private var viewModelJob = SupervisorJob()

    fun getMovies() {
        viewModelJob.cancelChildren()
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelJob) {
                if (pager.hasNextPage()) {
                    when (val result = interactor.getMoviesTopRated(page = pager.nextPage)) {
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

}

