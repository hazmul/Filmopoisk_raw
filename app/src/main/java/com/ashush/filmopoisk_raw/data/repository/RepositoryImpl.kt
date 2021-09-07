package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.domain.repository.IRepository
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val retrofit: RetrofitImpl, private val storage: IStorage) :
    IRepository {

    override suspend fun getConfiguration(api_key: String): Response<DataConfigurationModel> {
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

    override suspend fun getGenresInfo(api_key: String): Response<DataGenresInfo> {
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
        api_key: String,
        append_to_response: String?
    ): Response<DataMovieDetailModel> {
        return retrofit.retrofitService.getMovieDetail(movie_id, api_key, append_to_response)
    }

    override suspend fun getMoviesPopular(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesPopular(api_key, language, page, region)
    }

    override suspend fun getMoviesTopRated(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesTopRated(api_key, language, page, region)
    }

    override suspend fun getMoviesUpcoming(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesUpcoming(api_key, language, page, region)
    }

    override suspend fun getMoviesNowPlaying(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesNowPlaying(api_key, language, page, region)
    }

    override suspend fun getSearchResult(
        api_key: String,
        language: String?,
        query: String,
        page: String?,
        include_adult: Boolean?,
        region: String?,
        year: Int?,
        primary_release_year: Int?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getSearchResult(
            api_key,
            language,
            query,
            page,
            include_adult,
            region,
            year,
            primary_release_year
        )
    }

    override fun <T : BaseEntity> getAll(): List<BaseEntity>? {
        return storage.getAll<T>()
    }

    override fun <T : BaseEntity> getAllByIds(moviesId: List<Int>): List<BaseEntity>? {
        return storage.getAllByIds<T>(moviesId)
    }

    override fun <T : BaseEntity> updateMovies(moviesList: List<BaseEntity>) {
       storage.updateMovies<T>(moviesList)
    }

    override fun <T : BaseEntity> getById(movieId: Int): BaseEntity? {
        return storage.getById<T>(movieId)
    }

    override fun <T : BaseEntity> delete(movieList: BaseEntity) {
        storage.delete<T>(movieList)
    }

    override fun <T : BaseEntity> insertAll(moviesList: List<T>) {
        storage.insertAll(moviesList)
    }
}
