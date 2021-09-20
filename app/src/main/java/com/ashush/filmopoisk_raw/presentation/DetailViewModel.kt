package com.ashush.filmopoisk_raw.presentation

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
 * ViewModel экрана приложения с детальной информацией о фильме.
 * @param interactor интерактор для получения данных
 * @param dispatcherProvider провайдер CoroutineDispatcher для выбора потока выполнения
 * @property requestResult LiveData содержит информацию об фильме полученного по результатам запроса.
 * @property requestError LiveData содержит информацию об ошибке по результатам запроса.
 * @property inFavorite LiveData содержит информацию о наличии/отсутсвии фильма в категории "Favorite".
 * @property inWatchlist LiveData содержит информацию о наличии/отсутсвии фильма в категории "Watchlist".
 */

class DetailViewModel @Inject constructor(
    private var interactor: Interactor,
    private val dispatcherProvider: IDispatcherProvider
) : ViewModel() {

    val requestResult = MutableLiveData<DomainDetailedMovie>()
    val requestError = MutableLiveData<String>()
    val inFavorite = MutableLiveData<Boolean>()
    val inWatchlist = MutableLiveData<Boolean>()

    /**
     * Получить информацию о фильме
     * @param movieId - ID интересующего фильма
     * - При успехе обновить LiveData [requestResult]. Проверить наличие фильма в категориях "Favorite" и "Watchlist".
     * - При не успехе обновить LiveData [requestError]
     */
    fun getMovieInfo(movieId: Int) {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                when (val result = interactor.getMovieDetail(movieId)) {
                    is RequestResult.Success -> {
                        requestResult.postValue(result.data!!)
                        checkIsFavorite()
                        checkIsWatchlist()
                    }
                    is RequestResult.Error -> requestError.postValue(result.message!!)
                }
            }
        }
    }

    private fun checkIsFavorite() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                when (val result = requestResult.value?.let { interactor.getById(DataType.FAVORITES, it.id) }) {
                    is RequestResult.Success -> inFavorite.postValue(true)
                    else -> inFavorite.postValue(false)
                }
            }
        }
    }

    private fun checkIsWatchlist() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                when (val result = requestResult.value?.let { interactor.getById(DataType.WATCHLIST, it.id) }) {
                    is RequestResult.Success -> inWatchlist.postValue(true)
                    else -> inWatchlist.postValue(false)
                }
            }
        }
    }

    /**
     * Обработать действие пользователя по добавлению\удалению фильма из категории "Favorite"
     * Если в категории, то убрать, если нет, то добавить.
     */
    fun toFavoriteClicked(movieDomain: DomainDetailedMovie) {
        if (inFavorite.value == false) {
            viewModelScope.launch {
                withContext(dispatcherProvider.io) {
                    interactor.insert(DataType.FAVORITES, movieDomain)
                    checkIsFavorite()
                }
            }
        } else {
            viewModelScope.launch {
                withContext(dispatcherProvider.io) {
                    interactor.delete(DataType.FAVORITES, movieDomain)
                    checkIsFavorite()
                }
            }

        }
    }

    /**
     * Обработать действие пользователя по добавлению\удалению фильма из категории "Watchlist"
     * Если в категории, то убрать, если нет, то добавить.
     */
    fun toWatchlistClicked(movieDomain: DomainDetailedMovie) {
        if (inWatchlist.value == false) {
            viewModelScope.launch {
                withContext(dispatcherProvider.io) {
                    interactor.insert(DataType.WATCHLIST, movieDomain)
                    checkIsWatchlist()
                }
            }
        } else {
            viewModelScope.launch {
                withContext(dispatcherProvider.io) {
                    interactor.delete(DataType.WATCHLIST, movieDomain)
                    checkIsWatchlist()
                }
            }

        }
    }
}
