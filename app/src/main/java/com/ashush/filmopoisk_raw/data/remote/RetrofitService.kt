package com.ashush.filmopoisk_raw.data.remote

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.data.models.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.data.models.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.data.models.movies.DataMoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/3/configuration")
    suspend fun getConfiguration(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
    ): Response<DataConfigurationModel>

    @GET("/3/genre/movie/list")
    suspend fun getGenresInfo(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
    ): Response<DataGenresInfo>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("append_to_response") append_to_response: String? = null,
    ): Response<DataMovieDetailModel>

    @GET("/3/movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMoviesModel>

    @GET("/3/movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMoviesModel>

    @GET("/3/movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMoviesModel>

    @GET("/3/movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMoviesModel>

    @GET("/3/search/movie")
    suspend fun getSearchResult(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("query") query: String,
        @Query("page") page: Int? = 1,
        @Query("include_adult") includeAdult: Boolean? = null,
        @Query("region") region: String? = null,
        @Query("year") year: Int? = null,
        @Query("primary_release_year") primaryReleaseYear: Int? = null
    ): Response<DataMoviesModel>
}