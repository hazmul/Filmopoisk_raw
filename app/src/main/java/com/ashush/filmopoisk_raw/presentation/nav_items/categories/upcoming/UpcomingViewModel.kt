package com.ashush.filmopoisk_raw.presentation.nav_items.categories.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpcomingViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DataMoviesModel>()
    val requestError = MutableLiveData<String>()

    fun doRequest() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getMoviesUpcoming(api_key = DataConfig.API_KEY)
                when {
                    result.isSuccessful -> {
                        requestResult.postValue(result.body())
                    }
                    !result.isSuccessful -> {
                        requestError.value = result.message()
                    }

                }
            }
        }
    }
}