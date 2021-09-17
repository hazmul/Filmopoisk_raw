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

/**
 * ViewModel главного экрана приложения является доступной для фрагментов в рамках реализации sharedViewModel.
 * @param interactor интерактор для получения данных
 * @property viewTypeStatus LiveData содержит информацию о типе отображения подборок фильмов, например список или сетка.
 * @property optionMenuIsNeeded LiveData содержит информацию о необходимости отображения кнопок на тулбаре, на некоторых фрагментах это не требуется.
 */

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
