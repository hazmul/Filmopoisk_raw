package com.ashush.filmopoisk_raw.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.DataType
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DataMovieDetailModel>()
    val requestError = MutableLiveData<String>()
    val inFavorite = MutableLiveData<Boolean>()
    val inWatchlist = MutableLiveData<Boolean>()


    fun getMovieInfo(movieId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getMovieDetail(movieId)
                when {
                    result.isSuccessful -> {
                        requestResult.postValue(result.body())
                        checkIsFavorite()
                        checkIsWatchlist()
                    }
                    !result.isSuccessful -> {
                        requestError.postValue(result.message())
                    }
                }
            }
        }
    }

    private fun checkIsFavorite() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = requestResult.value?.id?.let { interactor.getById(DataType.FAVORITES, it) }
                if (result != null) {
                    inFavorite.postValue(true)
                } else {
                    inFavorite.postValue(false)
                }
                Log.d("TAG", "checkIsFavorite() called result = $result")
            }
        }
    }

    private fun checkIsWatchlist() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = requestResult.value?.id?.let { interactor.getById(DataType.WATCHLIST, it) }
                if (result != null) {
                    inWatchlist.postValue(true)
                } else {
                    inWatchlist.postValue(false)
                }
            }
        }
    }

    fun toFavoriteClicked(movie: DataMovieDetailModel) {
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
                    val result = interactor.delete(DataType.FAVORITES, movie)
                    Log.d("TAG", "toFavoriteClicked() called delete = $result")
                    checkIsFavorite()
                }
            }

        }
    }

    fun toWatchlistClicked(movie: DataMovieDetailModel) {
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
                    val result = interactor.delete(DataType.FAVORITES, movie)
                    Log.d("TAG", "toFavoriteClicked() called delete = $result")
                    checkIsFavorite()
                }
            }

        }
    }
}
