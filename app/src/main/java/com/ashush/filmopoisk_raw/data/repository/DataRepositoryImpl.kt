package com.ashush.filmopoisk_raw.data.repository

import android.util.Log
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.mapper.DetailedMovieMapper
import com.ashush.filmopoisk_raw.data.mapper.EntitiesMapper
import com.ashush.filmopoisk_raw.data.mapper.MoviesMapper
import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.datainterfaces.IDataRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageHandler
import com.ashush.filmopoisk_raw.domain.models.DataType
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.ashush.filmopoisk_raw.domain.models.Movies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val retrofitServiceProvider: RetrofitServiceProvider,
    private val storage: IStorage
) :
    IDataRepository {

    override suspend fun getConfiguration(): RequestResult<Boolean> {
        val result = retrofitServiceProvider.getService().getConfiguration()
        return when {
            result.isSuccessful -> {
                result.body()?.let {
                    storage.storeRemoteConfiguration(it)
                    DataConfig.config = it
                }
                RequestResult.Success(true)
            }
            else -> {
                DataConfig.config = storage.getRemoteConfiguration()
                RequestResult.Error(data = false, message = result.message())
            }
        }
    }

    override suspend fun getGenresInfo(): RequestResult<Boolean> {
        val result = retrofitServiceProvider.getService().getGenresInfo()
        return when {
            result.isSuccessful -> {
                result.body()?.let {
                    DataConfig.genres = it
                }
                RequestResult.Success(true)
            }
            else -> {
                RequestResult.Error(data = false, message = result.message())
            }
        }
    }

    override suspend fun getMovieDetail(
        movie_id: Int,
        appendToResponse: String?
    ): RequestResult<DetailedMovie> {
        val result = retrofitServiceProvider.getService()
            .getMovieDetail(movieId = movie_id, append_to_response = appendToResponse)
        return when {
            result.isSuccessful -> RequestResult.Success(DetailedMovieMapper.mapToDetailMovie(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesPopular(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<Movies> {
        val result = retrofitServiceProvider.getService().getMoviesPopular(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesTopRated(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<Movies> {
        val result = retrofitServiceProvider.getService().getMoviesTopRated(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesUpcoming(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<Movies> {
        val result = retrofitServiceProvider.getService().getMoviesUpcoming(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesNowPlaying(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<Movies> {
        val result = retrofitServiceProvider.getService().getMoviesNowPlaying(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getSearchResult(
        language: String?,
        query: String,
        page: Int?,
        includeAdult: Boolean?,
        region: String?,
        year: Int?,
        primaryReleaseYear: Int?
    ): RequestResult<Movies> {
        val result = retrofitServiceProvider.getService().getSearchResult(
            language = language,
            query = query,
            page = page,
            includeAdult = includeAdult,
            region = region,
            year = year,
            primaryReleaseYear = primaryReleaseYear,
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getStorageHandler(dataType: DataType): IStorageHandler {
        val dao = when (dataType) {
            DataType.FAVORITES -> storage.getFavoriteDao()
            DataType.WATCHLIST -> storage.getWatchlistDao()
        }
        return object : IStorageHandler {
            override suspend fun getAll(): RequestResult<List<DetailedMovie>> {
                val result = dao.getAll()
                return when {
                    result != null -> RequestResult.Success(result.map { EntitiesMapper.mapToDetailMovie(it) })
                    else -> RequestResult.Error(null, "Something wrong")
                }
            }

            override suspend fun getById(movieId: Int): RequestResult<DetailedMovie> {
                val result = dao.getById(movieId)
                return when {
                    result != null -> RequestResult.Success(EntitiesMapper.mapToDetailMovie(result))
                    else -> RequestResult.Error(null, "Something wrong")
                }
            }

            override suspend fun insert(movie: DetailedMovie): RequestResult<Long> {
                val result = when (dataType) {
                    DataType.FAVORITES -> storage.getFavoriteDao().insert(EntitiesMapper.mapToFavorites(movie))
                    DataType.WATCHLIST -> storage.getWatchlistDao().insert(EntitiesMapper.mapToWatchlist(movie))
                }
                return when {
                    result >= 0 -> RequestResult.Success(result)
                    else -> RequestResult.Error(null, "Something wrong")
                }
            }

            override suspend fun delete(movie: DetailedMovie): RequestResult<Int> {
                val result = when (dataType) {
                    DataType.FAVORITES -> storage.getFavoriteDao().delete(EntitiesMapper.mapToFavorites(movie))
                    DataType.WATCHLIST -> storage.getWatchlistDao().delete(EntitiesMapper.mapToWatchlist(movie))
                }
                return when {
                    result >= 0 -> RequestResult.Success(result)
                    else -> RequestResult.Error(null, "Something wrong")
                }
            }

            override suspend fun updateMovie(movie: DetailedMovie): RequestResult<Int> {
                val result = when (dataType) {
                    DataType.FAVORITES -> storage.getFavoriteDao().update(EntitiesMapper.mapToFavorites(movie))
                    DataType.WATCHLIST -> storage.getWatchlistDao().update(EntitiesMapper.mapToWatchlist(movie))
                }
                return when {
                    result >= 0 -> RequestResult.Success(result)
                    else -> RequestResult.Error(null, "Something wrong")
                }
            }

        }
    }

}

