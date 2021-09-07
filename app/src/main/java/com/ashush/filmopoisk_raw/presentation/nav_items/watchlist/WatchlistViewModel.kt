package com.ashush.filmopoisk_raw.presentation.nav_items.watchlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.storage.db.DBRoom
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WatchlistViewModel @Inject constructor(
    private var dbRoom: DBRoom,
    private var interactor: Interactor
) :
    ViewModel() {

    val requestResult = MutableLiveData<DataMoviesModel>()
    val requestError = MutableLiveData<String>()

    fun doRequest() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = DataMoviesModel(movies = interactor.getAll<Watchlist>()?.map {
                    DataMoviesModel.Movie(
                        id = it.movieId,
                        title = it.title,
                        popularity = it.popularity,
                        releaseDate = it.releaseDate,
                        overview = it.overview,
                        posterPath = it.posterPath
                    )
                })
                requestResult.postValue(result)
            }
        }
    }
}
