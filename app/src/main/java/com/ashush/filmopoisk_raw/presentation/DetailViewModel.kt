package com.ashush.filmopoisk_raw.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel экрана приложения с детальной информацией о фильме.
 * @param interactor интерактор для получения данных
 * @property requestResult LiveData содержит информацию об фильме полученного по результатам запроса.
 * @property requestError LiveData содержит информацию об ошибке по результатам запроса.
 * @property inFavorite LiveData содержит информацию о наличии/отсутсвии фильма в категории "Favorite".
 * @property inWatchlist LiveData содержит информацию о наличии/отсутсвии фильма в категории "Watchlist".
 */

class DetailViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DomainDetailedMovie>()
    val requestError = MutableLiveData<String>()
    val inFavorite = MutableLiveData<Boolean>()
    val inWatchlist = MutableLiveData<Boolean>()

    fun getMovieInfo(movieId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
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
            withContext(Dispatchers.IO) {
                when (val result = requestResult.value?.let { interactor.getById(DataType.FAVORITES, it.id) }) {
                    is RequestResult.Success -> inFavorite.postValue(true)
                    else -> inFavorite.postValue(false)
                }
            }
        }
    }

    private fun checkIsWatchlist() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = requestResult.value?.let { interactor.getById(DataType.WATCHLIST, it.id) }) {
                    is RequestResult.Success -> inWatchlist.postValue(true)
                    else -> inWatchlist.postValue(false)
                }
            }
        }
    }

    fun toFavoriteClicked(movieDomain: DomainDetailedMovie) {
        if (inFavorite.value == false) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.insert(DataType.FAVORITES, movieDomain)
                    checkIsFavorite()
                }
            }
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.delete(DataType.FAVORITES, movieDomain)
                    checkIsFavorite()
                }
            }

        }
    }

    fun toWatchlistClicked(movieDomain: DomainDetailedMovie) {
        if (inWatchlist.value == false) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.insert(DataType.WATCHLIST, movieDomain)
                    checkIsWatchlist()
                }
            }
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.delete(DataType.WATCHLIST, movieDomain)
                    checkIsWatchlist()
                }
            }

        }
    }
}
