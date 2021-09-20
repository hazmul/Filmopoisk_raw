package com.ashush.filmopoisk_raw.presentation.navitems.watchlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.IDispatcherProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel экрана приложения показывающий фильмы в категории "Watchlist".
 * @param interactor интерактор для получения данных
 * @param dispatcherProvider провайдер CoroutineDispatcher для выбора потока выполнения
 * @property requestResult LiveData содержит информацию об фильме полученного по результатам запроса.
 * @property requestError LiveData содержит информацию об ошибке по результатам запроса.
 */

class WatchlistViewModel @Inject constructor(
    private val interactor: Interactor,
    private val dispatcherProvider: IDispatcherProvider
) :
    ViewModel() {

    val requestResult = MutableLiveData<List<DomainDetailedMovie>>()
    val requestError = MutableLiveData<String>()

    /**
     * Получить все фильмы категории "Watchlist"
     * - При успехе обновить LiveData [requestResult]
     * - При не успехе обновить LiveData [requestError]
     */

    fun getMovies() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                when (val result = interactor.getAll(DataType.WATCHLIST)) {
                    is RequestResult.Success -> requestResult.postValue(result.data!!)
                    is RequestResult.Error -> requestError.postValue(result.message!!)
                }
            }
        }
    }

}