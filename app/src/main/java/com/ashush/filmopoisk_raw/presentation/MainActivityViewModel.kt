package com.ashush.filmopoisk_raw.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val interactor: Interactor
) : ViewModel() {

    val viewTypeStatus = MutableLiveData<AppConfig.ViewType>()
    val optionMenuIsNeeded = MutableLiveData<Boolean>()

    fun loadAppSettings() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val config = interactor.getAppConfiguration()
                viewTypeStatus.postValue(config.viewType)
            }
        }
    }

    fun changeViewType() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val config = interactor.getAppConfiguration()
                when (config.viewType) {
                    AppConfig.ViewType.LISTVIEW -> config.viewType = AppConfig.ViewType.GRIDVIEW
                    AppConfig.ViewType.GRIDVIEW -> config.viewType = AppConfig.ViewType.LISTVIEW
                }
                interactor.setAppConfiguration(config)
                val result = interactor.getAppConfiguration()
                viewTypeStatus.postValue(result.viewType)
            }
        }
    }
}
