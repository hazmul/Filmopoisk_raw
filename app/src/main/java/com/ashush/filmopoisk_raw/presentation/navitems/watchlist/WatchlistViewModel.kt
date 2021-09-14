package com.ashush.filmopoisk_raw.presentation.navitems.watchlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.data.models.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WatchlistViewModel @Inject constructor(
    private var interactor: Interactor
) :
    ViewModel() {

    val requestResult = MutableLiveData<List<DetailedMovie>>()
    val requestError = MutableLiveData<String>()

    fun getMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = interactor.getAll(DataType.WATCHLIST)) {
                    is RequestResult.Success -> requestResult.postValue(result.data!!)
                    is RequestResult.Error -> requestError.postValue(result.message!!)
                }
            }
        }
    }

}