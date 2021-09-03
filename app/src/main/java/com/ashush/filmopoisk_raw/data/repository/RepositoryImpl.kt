package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.repository.IRepository
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel

class RepositoryImpl(private val storage: IStorage) : IRepository {

    override fun getMoviesUpcoming(): DataMoviesModel? {
        val retrofitImpl = RetrofitImpl().retrofitService
        return retrofitImpl.getMoviesUpcoming(DataConfig.API_KEY).execute().body()
    }

    override fun getMoviesPopular(): DataMoviesModel? {
        val retrofitImpl = RetrofitImpl().retrofitService
        return retrofitImpl.getMoviesPopular(DataConfig.API_KEY).execute().body()
    }

    override fun getMoviesTopRated(): DataMoviesModel? {
        val retrofitImpl = RetrofitImpl().retrofitService
        return retrofitImpl.getMoviesTopRated(DataConfig.API_KEY).execute().body()
    }

    override fun getMoviesNowPlaying(): DataMoviesModel? {
        val retrofitImpl = RetrofitImpl().retrofitService
        return retrofitImpl.getMoviesNowPlaying(DataConfig.API_KEY).execute().body()
    }

    override fun getMovieDetail(
        id: Int,
        append_to_response: String?
    ): DataMovieDetailModel? {
        val retrofitImpl = RetrofitImpl().retrofitService
        return retrofitImpl.getMovieDetail(
            id,
            DataConfig.API_KEY,
            append_to_response
        ).execute().body()
    }

    override fun getSearchResult(
        api_key: String,
        language: String?,
        query: String,
        page: String?,
        include_adult: Boolean?,
        region: String?,
        year: Int?,
        primary_release_year: Int?
    ): DataMoviesModel? {
        val retrofitImpl = RetrofitImpl().retrofitService
        return retrofitImpl.getSearchResult(
            api_key,
            language,
            query,
            page,
            include_adult,
            region,
            year,
            primary_release_year
        ).execute().body()
    }

    override fun getConfiguration() {
        val retrofitImpl = RetrofitImpl().retrofitService
        try {
            retrofitImpl.getConfiguration(DataConfig.API_KEY).execute().body()?.let {
                storage.storeRemoteConfiguration(it)
                DataConfig.config = it
            }
        } catch (e: Exception) {
            DataConfig.config = storage.getRemoteConfiguration()
        }
    }

}