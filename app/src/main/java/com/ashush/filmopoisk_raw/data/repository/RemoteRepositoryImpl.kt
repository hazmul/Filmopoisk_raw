package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.mapper.DetailedMovieMapper
import com.ashush.filmopoisk_raw.data.mapper.MoviesMapper
import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.datainterfaces.IRemoteRepository
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.ashush.filmopoisk_raw.domain.models.RequestResult
import javax.inject.Inject

/**
 * Класс - реализация интерфеса [IRemoteRepository]
 * @property retrofitServiceProvider - сущность для обращений в сеть
 * @property storage - сущность для сохранения некоторых данных
 *
 * */
class RemoteRepositoryImpl @Inject constructor(
    private val retrofitServiceProvider: RetrofitServiceProvider,
    private val storage: IStorage
) : IRemoteRepository {

    /**
     * Запрашиваются данные через [retrofitServiceProvider]
     * если успешно, то сохраняет с помощью [storage] и присваивает их [DataConfig.config]
     * если не успешно, то пытается загрузить их из локального источника с помощью [storage] и присваивоить их [DataConfig.config]
     */
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

    /**
     * Запрашиваются данные через [retrofitServiceProvider]
     * если успешно, то присваивает их [DataConfig.config]
     * если не успешно, то возвращает сообщение об ошибке
     */
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
        language: String?,
        appendToResponse: String?
    ): RequestResult<DomainDetailedMovie> {
        val result =
            retrofitServiceProvider.getService()
                .getMovieDetail(movieId = movie_id, language = language, appendToResponse = appendToResponse)
        return when {
            result.isSuccessful -> RequestResult.Success(DetailedMovieMapper.mapToDomainDetailedMovie(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesPopular(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<DomainMovies> {
        val result = retrofitServiceProvider.getService().getMoviesPopular(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToDomainMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesTopRated(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<DomainMovies> {
        val result = retrofitServiceProvider.getService().getMoviesTopRated(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToDomainMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesUpcoming(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<DomainMovies> {
        val result = retrofitServiceProvider.getService().getMoviesUpcoming(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToDomainMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

    override suspend fun getMoviesNowPlaying(
        language: String?,
        page: Int?,
        region: String?
    ): RequestResult<DomainMovies> {
        val result = retrofitServiceProvider.getService().getMoviesNowPlaying(
            language = language,
            page = page,
            region = region
        )
        return when {
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToDomainMovies(result.body()!!))
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
    ): RequestResult<DomainMovies> {
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
            result.isSuccessful -> RequestResult.Success(MoviesMapper.mapToDomainMovies(result.body()!!))
            else -> RequestResult.Error(null, result.message())
        }
    }

}

