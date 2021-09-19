package com.ashush.filmopoisk_raw.presentation.navitems.categories.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.Pager
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * ViewModel фрагмента отображающий подборку фильмов в категории "Upcoming"
 */

class UpcomingViewModel @Inject constructor(
    private var interactor: Interactor
) : ViewModel() {

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    val requestResult = MutableLiveData<List<DomainMovies.Movie>>()
    val requestError = MutableLiveData<String>()

    private val pager = Pager()
    private var viewModelJob = SupervisorJob()

    /**
     * Получить все фильмы категории "Upcoming"
     * - При успехе обновить LiveData [requestResult]
     * - При не успехе обновить LiveData [requestError]
     */

    fun getMovies() {
        viewModelJob.cancelChildren()
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelJob) {
                if (pager.hasNextPage()) {
                    when (val result = interactor.getMoviesUpcoming(page = pager.nextPage)) {
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

