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

    val recyclerViewType = MutableLiveData<Boolean>()


    fun getDomainConfiguration() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                interactor.getDomainConfiguration()
            }
        }
    }
}
