package com.ashush.filmopoisk_raw.data.repository

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.repository.IRepository
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val retrofit: RetrofitImpl, private val storage: IStorage) :
    IRepository {

    override fun getConfiguration(api_key: String): Response<DataConfigurationModel> {
        val result = retrofit.retrofitService.getConfiguration(DataConfig.API_KEY).execute()
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

    override fun getMovieDetail(
        movie_id: Int,
        api_key: String,
        append_to_response: String?
    ): Response<DataMovieDetailModel> {
        return retrofit.retrofitService.getMovieDetail(movie_id, api_key, append_to_response).execute()
    }

    override fun getMoviesPopular(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesPopular(api_key, language, page, region).execute()
    }

    override fun getMoviesTopRated(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesTopRated(api_key, language, page, region).execute()
    }

    override fun getMoviesUpcoming(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesUpcoming(api_key, language, page, region).execute()
    }

    override fun getMoviesNowPlaying(
        api_key: String,
        language: String?,
        page: String?,
        region: String?
    ): Response<DataMoviesModel> {
        return retrofit.retrofitService.getMoviesNowPlaying(api_key, language, page, region).execute()
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
        ).execute()
    }

}