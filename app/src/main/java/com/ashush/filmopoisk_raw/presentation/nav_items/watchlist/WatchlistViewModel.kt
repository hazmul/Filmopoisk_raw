package com.ashush.filmopoisk_raw.presentation.nav_items.watchlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.db.DBRoom
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
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
                val watchlistDao = dbRoom.watchlistDao()
                val resultDB = watchlistDao.getAll()
                val resultHTTP: MutableList<DataMoviesModel.Movie?> = mutableListOf()
                for (movie in resultDB) {
                    val result = interactor.getMovieDetail(movie.movieId!!, DataConfig.API_KEY)
                    when {
                        result.isSuccessful -> {
                            resultHTTP.add(
                                DataMoviesModel.Movie(
                                    title = result.body()?.title,
                                    posterPath = result.body()?.posterPath
                                )
                            )
                            requestResult.value = DataMoviesModel(movies = resultHTTP)
                        }
                        !result.isSuccessful -> {
                            requestError.value = result.errorBody().toString()
                        }
                    }
                }

            }
        }
    }
}