package com.ashush.filmopoisk_raw.presentation.launch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import com.ashush.filmopoisk_raw.utils.IDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel экрана запуска приложения.
 * @param interactor интерактор для получения данных
 * @param dispatcherProvider провайдер CoroutineDispatcher для выбора потока выполнения
 */
class LaunchViewModel @Inject constructor(
    private val interactor: Interactor,
    private val dispatcherProvider: IDispatcherProvider
) : ViewModel() {

    val requestResult = MutableLiveData<Boolean>()
    val requestError = MutableLiveData<String>()

    init {
        loadRemoteConfiguration()
    }

    private fun loadRemoteConfiguration() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                when (val result = interactor.getRemoteConfiguration()) {
                    is RequestResult.Success -> {
                        requestResult.postValue(result.data!!)
                    }
                    else -> {
                        requestError.postValue(result.message!!)
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