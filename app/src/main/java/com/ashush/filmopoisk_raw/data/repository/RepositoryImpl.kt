package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.mapper.MapperDB
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist
import com.ashush.filmopoisk_raw.domain.data_interfaces.IDBHandler
import com.ashush.filmopoisk_raw.domain.data_interfaces.IRepository
import com.ashush.filmopoisk_raw.domain.interactor.DataType
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val retrofit: RetrofitImpl, private val storage: IStorage) :
    IRepository {

    override suspend fun getConfiguration(): Response<DataConfigurationModel> {
        val result = retrofit.retrofitService.getConfiguration(DataConfig.API_KEY)
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
        val result = retrofit.retrofitService.getGenresInfo(DataConfig.API_KEY)
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
        return retrofit.retrofitService.getMovieDetail(movie_id, DataConfig.API_KEY, append_to_response)
    }

    override suspend fun getMoviesPopular(
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesPopular(DataConfig.API_KEY, language, page, region)
    }

    override suspend fun getMoviesTopRated(
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesTopRated(DataConfig.API_KEY, language, page, region)
    }

    override suspend fun getMoviesUpcoming(
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesUpcoming(DataConfig.API_KEY, language, page, region)
    }

    override suspend fun getMoviesNowPlaying(
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesNowPlaying(DataConfig.API_KEY, language, page, region)
    }

    override suspend fun getSearchResult(
        language: String?,
        query: String,
        page: String?,
        include_adult: Boolean?,
        region: String?,
        year: Int?,
        primary_release_year: Int?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getSearchResult(
            DataConfig.API_KEY,
            language,
            query,
            page,
            include_adult,
            region,
            year,
            primary_release_year
        )
    }

    override suspend fun getDBHandler(dataType: DataType): IDBHandler {
        val dao =
            when (dataType) {
                DataType.FAVORITES -> storage.getFavoriteDao()
                DataType.WATCHLIST -> storage.getWatchlistDao()
            }
        return object : IDBHandler {
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

