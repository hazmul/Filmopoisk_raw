package com.ashush.filmopoisk_raw.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DetailedMovie>()
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

    fun toFavoriteClicked(movie: DetailedMovie) {
        if (inFavorite.value == false) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.insert(DataType.FAVORITES, movie)
                    checkIsFavorite()
                }
            }
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.delete(DataType.FAVORITES, movie)
                    checkIsFavorite()
                }
            }

        }
    }

    fun toWatchlistClicked(movie: DetailedMovie) {
        if (inWatchlist.value == false) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.insert(DataType.WATCHLIST, movie)
                    checkIsWatchlist()
                }
            }
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    interactor.delete(DataType.WATCHLIST, movie)
                    checkIsWatchlist()
                }
            }

        }
    }
}
