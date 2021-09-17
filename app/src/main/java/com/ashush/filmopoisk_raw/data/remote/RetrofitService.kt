package com.ashush.filmopoisk_raw.data.remote

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.data.models.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.data.models.movies.DataDetailedMovie
import com.ashush.filmopoisk_raw.data.models.movies.DataMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Интерфейс формирующий запросы в сеть
 */
interface RetrofitService {

    /**
     * Получить настройки API
     * @param [apiKey] уникальный ключ для запроса
     * @return сущность [Response]
     */
    @GET("/3/configuration")
    suspend fun getConfiguration(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
    ): Response<DataConfigurationModel>

    /**
     * Получить информацию по жанрам
     * @param [apiKey] уникальный ключ для запроса
     * @param [language] для указания языка ответа
     * @return сущность [Response]
     */
    @GET("/3/genre/movie/list")
    suspend fun getGenresInfo(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
    ): Response<DataGenresInfo>

    /**
     * Получить детальную информацию по фильму
     * @param [movieId] уникальный id фильма
     * @param [apiKey] уникальный ключ для запроса
     * @param [language] для указания языка ответа
     * @param [appendToResponse] для указания дополнительной информации
     * @return сущность [Response]
     */
    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("append_to_response") appendToResponse: String? = null,
    ): Response<DataDetailedMovie>

    /**
     * Получить список фильмов категории "популярные"
     * @param [apiKey] уникальный ключ для запроса
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return сущность [Response]
     */
    @GET("/3/movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMovies>

    /**
     * Получить список фильмов категории "высокооцененные"
     * @param [apiKey] уникальный ключ для запроса
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return сущность [Response]
     */
    @GET("/3/movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMovies>

    /**
     * Получить список фильмов категории "скоро в прокате"
     * @param [apiKey] уникальный ключ для запроса
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return сущность [Response]
     */
    @GET("/3/movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMovies>

    /**
     * Получить список фильмов категории "сейчас в прокате"
     * @param [apiKey] уникальный ключ для запроса
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return сущность [Response]
     */
    @GET("/3/movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String = DataConfig.API_KEY,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = 1,
        @Query("region") region: String? = null
    ): Response<DataMovies>

    /**
     * Получить список фильмов исходя из критериев запроса
     * @param [apiKey] уникальный ключ для запроса
     * @param [language] для указания языка ответа
     * @param [query] для указания ключевых символов
     * @param [page] для запроса конкретной страницы
     * @param [includeAdult] для указания будет ли в ответе взрослый контент
     * @param [region] для запроса информации по региону/стране
     * @param [year] для указания года релиза фильма
     * @param [primaryReleaseYear] для указания основного года релиза фильма
     * @return сущность [Response]
     */
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
    ): Response<DataMovies>
}