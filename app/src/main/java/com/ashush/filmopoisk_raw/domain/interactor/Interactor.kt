package com.ashush.filmopoisk_raw.domain.interactor

import com.ashush.filmopoisk_raw.domain.datainterfaces.IRemoteRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import com.ashush.filmopoisk_raw.domain.models.*
import javax.inject.Inject

/**
 * Класс реализующий часть логики бизнес слоя
 * @param remoteRepository для осуществления запросов к удаленным источникам данных
 * @param storageRepository для осуществления запросов к локальным источникам данных
 */

class Interactor @Inject constructor(
    private val remoteRepository: IRemoteRepository,
    private val storageRepository: IStorageRepository,
) {
    /**
     * Получить настройки API
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getRemoteConfiguration(): RequestResult<Boolean> {
        return remoteRepository.getConfiguration()
    }

    /**
     * Получить информацию по жанрам
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getGenresInfo(): RequestResult<Boolean> {
        return remoteRepository.getGenresInfo()
    }

    /**
     * Получить детальную информацию по фильму
     * @param [movieId] уникальный id фильма
     * @param [language] для указания языка ответа
     * @param [appendToResponse] для указания дополнительной информации
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMovieDetail(
        movieId: Int,
        language: String? = null,
        appendToResponse: String? = null,
    ): RequestResult<DomainDetailedMovie> {
        return remoteRepository.getMovieDetail(movieId, language, appendToResponse)
    }

    /**
     * Получить список фильмов категории "популярные"
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesPopular(language, page, region)
    }

    /**
     * Получить список фильмов категории "высокооцененные"
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesTopRated(language, page, region)
    }

    /**
     * Получить список фильмов категории "скоро в прокате"
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesUpcoming(language, page, region)
    }

    /**
     * Получить список фильмов категории "сейчас в прокате"
     * @param [language] для указания языка ответа
     * @param [page] для запроса конкретной страницы
     * @param [region] для запроса информации по региону/стране
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesNowPlaying(language, page, region)
    }

    /**
     * Получить список фильмов исходя из критериев запроса
     * @param [language] для указания языка ответа
     * @param [query] для указания ключевых символов
     * @param [page] для запроса конкретной страницы
     * @param [includeAdult] для указания будет ли в ответе взрослый контент
     * @param [region] для запроса информации по региону/стране
     * @param [year] для указания года релиза фильма
     * @param [primaryReleaseYear] для указания основного года релиза фильма
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
    ): RequestResult<DomainMovies> {
        return remoteRepository.getSearchResult(
            language,
            query,
            page,
            includeAdult,
            region,
            year,
            primaryReleaseYear
        )
    }

    /**
     * Получить настройки приложения из хранилища
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getAppConfiguration(): AppConfig {
        AppConfig.setInstance(storageRepository.getAppConfiguration().data!!)
        return AppConfig.getInstance()!!
    }

    /**
     * Задать настройки приложения в [AppConfig] и после сохранить их в локальном хранилище
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun setAppConfiguration(config: AppConfig): RequestResult<Boolean> {
        AppConfig.setInstance(config)
        return storageRepository.storeAppConfiguration(AppConfig.getInstance()!!)
    }

    /**
     * Получить все данные заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getAll(dataType: DataType): RequestResult<List<DomainDetailedMovie>> {
        return storageRepository.getAll(dataType)
    }

    /**
     * Получить данные по id заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun getById(dataType: DataType, movieId: Int): RequestResult<DomainDetailedMovie> {
        return storageRepository.getById(movieId, dataType)
    }

    /**
     * Сохранить объект [DomainDetailedMovie] в заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun insert(dataType: DataType, movieDomain: DomainDetailedMovie) {
        storageRepository.insert(movieDomain, dataType)
    }

    /**
     * Удалить объект [DomainDetailedMovie] в заданной категории
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun delete(dataType: DataType, movieDomain: DomainDetailedMovie): RequestResult<Int> {
        return storageRepository.delete(movieDomain, dataType)
    }

    /**
     * Обновить объект [DomainDetailedMovie] в заданной категории при его наличии
     * [dataType] - заданная категория
     * @return объект [RequestResult] со статусом выполнения задачи
     */
    suspend fun updateMovie(dataType: DataType, movieDomain: DomainDetailedMovie): RequestResult<Int> {
        return storageRepository.updateMovie(movieDomain, dataType)
    }

}