package com.ashush.filmopoisk_raw.domain.datainterfaces

import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.ashush.filmopoisk_raw.domain.models.RequestResult

/**
 *
 * Интерфейс репозитория для получения данных с внешних сетевых источников
 *
 */
interface IRemoteRepository {
    /**
     * Получить настройки API
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getConfiguration(): RequestResult<Boolean>

    /**
     * Получить информацию по жанрам фильмов
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getGenresInfo(): RequestResult<Boolean>

    /**
     * Получить детальную информацию по фильму
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMovieDetail(
        movie_id: Int,
        appendToResponse: String? = null,
    ): RequestResult<DomainDetailedMovie>

    /**
     * Получить список фильмов категории "популярные"
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    /**
     * Получить список фильмов категории "высокооцененные"
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    /**
     * Получить список фильмов категории "скоро в прокате"
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    /**
     * Получить список фильмов категории "сейчас в прокате"
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies>

    /**
     * Получить список фильмов исходя из критериев запроса
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getSearchResult(
        language: String? = null,
        query: String,
        page: Int? = null,
        includeAdult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primaryReleaseYear: Int? = null
    ): RequestResult<DomainMovies>

}