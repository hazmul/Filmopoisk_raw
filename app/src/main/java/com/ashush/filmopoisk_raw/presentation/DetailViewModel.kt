package com.ashush.filmopoisk_raw.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DataMovieDetailModel>()
    val requestError = MutableLiveData<String>()

    fun doRequest(movieId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getMovieDetail(movieId, DataConfig.API_KEY)
                when {
                    result.isSuccessful -> {
                        requestResult.postValue(result.body())
                    }
                    !result.isSuccessful -> {
                        requestError.postValue(result.message())
                    }
                }
            }
        }
    }
}
