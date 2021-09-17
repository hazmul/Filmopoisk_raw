package com.ashush.filmopoisk_raw.domain.interactor

import com.ashush.filmopoisk_raw.domain.datainterfaces.IRemoteRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import com.ashush.filmopoisk_raw.domain.models.*
import javax.inject.Inject

class Interactor @Inject constructor(
    private val remoteRepository: IRemoteRepository,
    private val storageRepository: IStorageRepository,
) {

    suspend fun getRemoteConfiguration(): RequestResult<Boolean> {
        return remoteRepository.getConfiguration()
    }

    suspend fun getGenresInfo(): RequestResult<Boolean> {
        return remoteRepository.getGenresInfo()
    }

    suspend fun getMovieDetail(
        movie_id: Int,
        append_to_response: String? = null,
    ): RequestResult<DomainDetailedMovie> {
        return remoteRepository.getMovieDetail(movie_id, append_to_response)
    }

    suspend fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesPopular(language, page, region)
    }

    suspend fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesTopRated(language, page, region)
    }

    suspend fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesUpcoming(language, page, region)
    }

    suspend fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getMoviesNowPlaying(language, page, region)
    }

    suspend fun getSearchResult(
        language: String? = null,
        query: String,
        page: Int? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): RequestResult<DomainMovies> {
        return remoteRepository.getSearchResult(
            language,
            query,
            page,
            include_adult,
            region,
            year,
            primary_release_year
        )
    }


    suspend fun getAppConfiguration(): AppConfig {
        if (AppConfig.getInstance() == null) {
            AppConfig.setInstance(storageRepository.getAppConfiguration().data!!)
        }
        return AppConfig.getInstance()!!
    }

    suspend fun setAppConfiguration(config: AppConfig): Boolean {
        AppConfig.setInstance(config)
        storageRepository.storeAppConfiguration(AppConfig.getInstance()!!)
        return true
    }

    suspend fun getAll(dataType: DataType): RequestResult<List<DomainDetailedMovie>> {
        return storageRepository.getAll(dataType)
    }

    suspend fun getById(dataType: DataType, movieId: Int): RequestResult<DomainDetailedMovie> {
        return storageRepository.getById(movieId, dataType)
    }

    suspend fun insert(dataType: DataType, movieDomain: DomainDetailedMovie) {
        storageRepository.insert(movieDomain, dataType)
    }

    suspend fun delete(dataType: DataType, movieDomain: DomainDetailedMovie): RequestResult<Int> {
        return storageRepository.delete(movieDomain, dataType)
    }

    suspend fun updateMovie(dataType: DataType, movieDomain: DomainDetailedMovie): RequestResult<Int> {
        return storageRepository.updateMovie(movieDomain, dataType)
    }

}