package com.ashush.filmopoisk_raw.presentation.navitems.categories.nowPlaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.IDispatcherProvider
import com.ashush.filmopoisk_raw.utils.Pager
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel фрагмента отображающий подборку фильмов в категории "NowPlaying"
 * @param interactor интерактор для получения данных
 * @param dispatcherProvider провайдер CoroutineDispatcher для выбора потока выполнения
 */

class NowPlayingViewModel @Inject constructor(
    private val interactor: Interactor,
    private val dispatcherProvider: IDispatcherProvider
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
     * Получить все фильмы категории "NowPlaying"
     * - При успехе обновить LiveData [requestResult]
     * - При не успехе обновить LiveData [requestError]
     */

    fun getMovies() {
        viewModelJob.cancelChildren()
        viewModelScope.launch {
            withContext(dispatcherProvider.io + viewModelJob) {
                if (pager.hasNextPage()) {
                    when (val result = interactor.getMoviesNowPlaying(page = pager.nextPage)) {
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

