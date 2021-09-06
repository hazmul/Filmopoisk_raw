package com.ashush.filmopoisk_raw.presentation.nav_items.launch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LaunchViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData(false)
    val requestError = MutableLiveData<String>()

    fun doRequest() {
        val resultList = mutableListOf<Boolean>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getConfiguration(api_key = DataConfig.API_KEY)
                when {
                    result.isSuccessful -> {
                        resultList.add(true)
                    }
                    !result.isSuccessful -> {
                        requestError.postValue(result.message())
                    }
                }
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getGenresInfo(api_key = DataConfig.API_KEY)
                when {
                    result.isSuccessful -> {
                        resultList.add(true)
                    }
                    !result.isSuccessful -> {
                        requestError.postValue(result.message())
                    }
                }
            }
        }
        if (resultList.all { it }) {
            requestResult.postValue(true)
        }
    }
}