package com.ashush.filmopoisk_raw.presentation.nav_items.launch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.config.DomainConfig
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LaunchViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<Boolean>()
    val requestError = MutableLiveData<String>()

    init {
        loadRemoteConfiguration()
    }

    private fun loadRemoteConfiguration() {
        val resultList = mutableListOf<Boolean>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getRemoteConfiguration()
                when {
                    result.isSuccessful -> {
                        resultList.add(true)
                    }
                    !result.isSuccessful -> {
                        requestError.postValue(result.message())
                    }
                }
                checkLoading(resultList)
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getGenresInfo()
                when {
                    result.isSuccessful -> {
                        resultList.add(true)
                    }
                    !result.isSuccessful -> {
                        requestError.postValue(result.message())
                    }
                }
                checkLoading(resultList)
            }
        }
    }

    private fun checkLoading(resultList: List<Boolean>) {
        if (resultList.all { it } && resultList.size == 2) {
            requestResult.postValue(true)
        }
    }

}