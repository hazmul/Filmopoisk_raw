package com.ashush.filmopoisk_raw.presentation.nav_items.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(private var interactor: Interactor) : ViewModel() {

    val requestResult = MutableLiveData<DataMoviesModel>()
    val requestError = MutableLiveData<String>()

    fun doRequest(query: String) {
        viewModelScope.launch {
            delay(1000)
            withContext(Dispatchers.IO) {
                val result = interactor.getSearchResult(query = query)
                when {
                    result.isSuccessful -> {
                        requestResult.postValue(result.body())
                    }

                }
            }
        }
    }
}

