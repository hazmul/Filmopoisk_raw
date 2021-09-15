package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.mapper.DetailedMovieMapper
import com.ashush.filmopoisk_raw.data.mapper.EntitiesMapper
import com.ashush.filmopoisk_raw.data.mapper.MoviesMapper
import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.datainterfaces.IDataRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
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



}

