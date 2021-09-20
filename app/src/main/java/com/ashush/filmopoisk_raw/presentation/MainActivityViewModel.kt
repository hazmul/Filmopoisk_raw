package com.ashush.filmopoisk_raw.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import com.ashush.filmopoisk_raw.utils.IDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel главного экрана приложения является доступной для фрагментов в рамках реализации sharedViewModel.
 * @param interactor интерактор для получения данных
 * @param dispatcherProvider провайдер CoroutineDispatcher для выбора потока выполнения
 * @property viewTypeStatus LiveData содержит информацию о типе отображения подборок фильмов, например список или сетка.
 * @property optionMenuIsNeeded LiveData содержит информацию о необходимости отображения кнопок на тулбаре, на некоторых фрагментах это не требуется.
 */

class MainActivityViewModel @Inject constructor(
    private val interactor: Interactor,
    private val dispatcherProvider: IDispatcherProvider
) : ViewModel() {

    val viewTypeStatus = MutableLiveData<AppConfig.ViewType>()
    val themeStatus = MutableLiveData<Boolean>()
    val optionMenuIsNeeded = MutableLiveData<Boolean>()

    fun loadAppSettings() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                val config = interactor.getAppConfiguration()
                viewTypeStatus.postValue(config.viewType)
                themeStatus.postValue(config.themeType)
            }
        }
    }

    fun changeViewType() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
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
