package com.ashush.filmopoisk_raw.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.mapper.MapperDB
import com.ashush.filmopoisk_raw.data.remote.MoviesListPagingSource
import com.ashush.filmopoisk_raw.data.remote.MoviesSearchPagingSource
import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.datainterfaces.IDataRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageHandler
import com.ashush.filmopoisk_raw.models.domain.DataType
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val retrofitServiceProvider: RetrofitServiceProvider,
    private val storage: IStorage
) :
    IDataRepository {

    private val defaultPagerConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

    override suspend fun getConfiguration(): Response<DataConfigurationModel> {
        val result = retrofitServiceProvider.getService().getConfiguration(DataConfig.API_KEY)
        when {
            result.isSuccessful -> {
                result.body()?.let {
                    storage.storeRemoteConfiguration(it)
                    DataConfig.config = it
                }
            }
            !result.isSuccessful -> {
                DataConfig.config = storage.getRemoteConfiguration()
            }
        }
        return result
    }

    override suspend fun getGenresInfo(): Response<DataGenresInfo> {
        val result = retrofitServiceProvider.getService().getGenresInfo(DataConfig.API_KEY)
        when {
            result.isSuccessful -> {
                result.body()?.let {
                    DataConfig.genres = it
                }
            }
        }
        return result
    }

    override suspend fun getMovieDetail(
        movie_id: Int,
        append_to_response: String?
    ): Response<DataMovieDetailModel> {
        return retrofitServiceProvider.getService().getMovieDetail(movie_id, DataConfig.API_KEY, append_to_response)
    }

    override fun getMoviesPopular(
        language: String?,
        page: Int?,
        region: String?
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return Pager(
            config = defaultPagerConfig,
            pagingSourceFactory = {
                MoviesListPagingSource(
                    retrofitServiceProvider.getService(),
                    MoviesListPagingSource.MoviesParameters(language, page, region),
                    MoviesListPagingSource.MoviesListRequests.Popular
                )
            }
        ).liveData
    }

    override fun getMoviesTopRated(
        language: String?,
        page: Int?,
        region: String?
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return Pager(
            config = defaultPagerConfig,
            pagingSourceFactory = {
                MoviesListPagingSource(
                    retrofitServiceProvider.getService(),
                    MoviesListPagingSource.MoviesParameters(language, page, region),
                    MoviesListPagingSource.MoviesListRequests.TopRated
                )
            }
        ).liveData
    }

    override fun getMoviesUpcoming(
        language: String?,
        page: Int?,
        region: String?
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return Pager(
            config = defaultPagerConfig,
            pagingSourceFactory = {
                MoviesListPagingSource(
                    retrofitServiceProvider.getService(),
                    MoviesListPagingSource.MoviesParameters(language, page, region),
                    MoviesListPagingSource.MoviesListRequests.Upcoming
                )
            }
        ).liveData
    }

    override fun getMoviesNowPlaying(
        language: String?,
        page: Int?,
        region: String?
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return Pager(
            config = defaultPagerConfig,
            pagingSourceFactory = {
                MoviesListPagingSource(
                    retrofitServiceProvider.getService(),
                    MoviesListPagingSource.MoviesParameters(language, page, region),
                    MoviesListPagingSource.MoviesListRequests.NowPlaying
                )
            }
        ).liveData
    }

    override fun getSearchResult(
        language: String?,
        query: String,
        page: Int?,
        include_adult: Boolean?,
        region: String?,
        year: Int?,
        primary_release_year: Int?
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return Pager(
            config = defaultPagerConfig,
            pagingSourceFactory = {
                MoviesSearchPagingSource(
                    retrofitServiceProvider.getService(),
                    MoviesSearchPagingSource.MovieParameters(
                        language,
                        query,
                        page,
                        include_adult,
                        region,
                        year,
                        primary_release_year
                    )
                )
            }
        ).liveData
    }

    override suspend fun getStorageHandler(dataType: DataType): IStorageHandler {
        val dao =
            when (dataType) {
                DataType.FAVORITES -> storage.getFavoriteDao()
                DataType.WATCHLIST -> storage.getWatchlistDao()
            }
        return object : IStorageHandler {
            override suspend fun getAll(): List<DataMovieDetailModel>? {
                return dao.getAll()?.map { it -> MapperDB.mapToDataMovieDetail(it) }
            }

            override suspend fun getById(movieId: Int): DataMovieDetailModel? {
                return dao.getById(movieId)?.let { it -> MapperDB.mapToDataMovieDetail(it) }
            }

            override suspend fun insert(movie: DataMovieDetailModel) {
                when (dataType) {
                    DataType.FAVORITES -> storage.getFavoriteDao().insert(MapperDB.mapToFavorites(movie))
                    DataType.WATCHLIST -> storage.getWatchlistDao().insert(MapperDB.mapToWatchlist(movie))
                }
            }

            override suspend fun delete(movie: DataMovieDetailModel): Int {
                return when (dataType) {
                    DataType.FAVORITES -> storage.getFavoriteDao().delete(MapperDB.mapToFavorites(movie))
                    DataType.WATCHLIST -> storage.getWatchlistDao().delete(MapperDB.mapToWatchlist(movie))
                }
            }

            override suspend fun updateMovie(movie: DataMovieDetailModel): Int {
                return when (dataType) {
                    DataType.FAVORITES -> storage.getFavoriteDao().update(MapperDB.mapToFavorites(movie))
                    DataType.WATCHLIST -> storage.getWatchlistDao().update(MapperDB.mapToWatchlist(movie))
                }
            }

        }
    }

}

