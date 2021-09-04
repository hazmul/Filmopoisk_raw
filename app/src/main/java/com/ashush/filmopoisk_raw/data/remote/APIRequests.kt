package com.ashush.filmopoisk_raw.data.remote

import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.utils.MyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIRequests {



    @GET("/3/configuration")
    fun getConfiguration(@Query("api_key") api_key: String): Call<DataConfigurationModel>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("append_to_response") append_to_response: String? = null,
    ): Call<DataMovieDetailModel>

    @GET("/3/movie/popular")
    fun getMoviesPopular(
        @Query("api_key") api_key: String,
        @Query("language") language: String? = null,
        @Query("page") page: String? = null,
        @Query("region") region: String? = null
    ): Call<DataMoviesModel>

    @GET("/3/movie/top_rated")
    fun getMoviesTopRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String? = null,
        @Query("page") page: String? = null,
        @Query("region") region: String? = null
    ): Call<DataMoviesModel>

    @GET("/3/movie/upcoming")
    fun getMoviesUpcoming(
        @Query("api_key") api_key: String,
        @Query("language") language: String? = null,
        @Query("page") page: String? = null,
        @Query("region") region: String? = null
    ): Call<DataMoviesModel>

    @GET("/3/movie/now_playing")
    fun getMoviesNowPlaying(
        @Query("api_key") api_key: String,
        @Query("language") language: String? = null,
        @Query("page") page: String? = null,
        @Query("region") region: String? = null
    ): Call<DataMoviesModel>

    @GET("/3/search/movie")
    fun getSearchResult(
        @Query("api_key") api_key: String,
        @Query("language") language: String? = null,
        @Query("query") query: String,
        @Query("page") page: String? = null,
        @Query("include_adult") include_adult: Boolean? = null,
        @Query("region") region: String? = null,
        @Query("year") year: Int? = null,
        @Query("primary_release_year") primary_release_year: Int? = null
    ): Call<DataMoviesModel>
}