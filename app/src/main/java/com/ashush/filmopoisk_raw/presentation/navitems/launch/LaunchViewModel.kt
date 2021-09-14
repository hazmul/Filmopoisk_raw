package com.ashush.filmopoisk_raw.presentation.navitems.launch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import kotlinx.coroutines.Dispatchers
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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = interactor.getRemoteConfiguration()
                when {
                    result.isSuccessful -> {
                        requestResult.postValue(true)
                    }
                    !result.isSuccessful -> {
                        requestError.postValue(result.message())
                        requestResult.postValue(true)
                    }
                }
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
              interactor.getGenresInfo()
            }
        }
    }
}