package com.ashush.filmopoisk_raw.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.config.DomainConfig
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val interactor: Interactor) : ViewModel() {

    val viewTypeLiveData = MutableLiveData<DomainConfig.ViewType>()

    fun setAppSettings(
        downloadImageAllowed: Boolean? = null,
        cacheImageAllowed: Boolean? = null,
        themeType: Boolean? = null,
        enableNotification: Boolean? = null,
        recyclerViewType: DomainConfig.ViewType? = null,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val config = interactor.getDomainConfiguration()
                downloadImageAllowed?.let { config.downloadImageAllowed = downloadImageAllowed }
                cacheImageAllowed?.let { config.cacheImageAllowed = cacheImageAllowed }
                themeType?.let { config.themeType = themeType }
                enableNotification?.let { config.enableNotification = enableNotification }
                recyclerViewType?.let { config.recyclerViewType = recyclerViewType }

                viewTypeLiveData.postValue(interactor.getDomainConfiguration().recyclerViewType)
            }
        }
    }
}
