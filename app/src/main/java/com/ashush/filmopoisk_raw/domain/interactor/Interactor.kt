package com.ashush.filmopoisk_raw.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ashush.filmopoisk_raw.models.domain.DomainConfig
import com.ashush.filmopoisk_raw.domain.datainterfaces.IDataRepository
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.models.data.configuration.DataGenresInfo
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.models.domain.DataType
import retrofit2.Response
import javax.inject.Inject

class Interactor @Inject constructor(
    private val dataRepository: IDataRepository
) {

    suspend fun getRemoteConfiguration(): Response<DataConfigurationModel> {
        return dataRepository.getConfiguration()
    }

    fun getDomainConfiguration(): DomainConfig {
        return DomainConfig.getInstance()
    }

    suspend fun getGenresInfo(): Response<DataGenresInfo> {
        return dataRepository.getGenresInfo()
    }

    suspend fun getMovieDetail(
        movie_id: Int,
        append_to_response: String? = null,
    ): Response<DataMovieDetailModel> {
        return dataRepository.getMovieDetail(movie_id, append_to_response)
    }

    fun getMoviesPopular(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return dataRepository.getMoviesPopular(language, page, region)
    }

    fun getMoviesTopRated(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return dataRepository.getMoviesTopRated(language, page, region)
    }

    fun getMoviesUpcoming(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return dataRepository.getMoviesUpcoming(language, page, region)
    }

    fun getMoviesNowPlaying(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return dataRepository.getMoviesNowPlaying(language, page, region)
    }

    fun getSearchResult(
        language: String? = null,
        query: String,
        page: Int? = null,
        include_adult: Boolean? = null,
        region: String? = null,
        year: Int? = null,
        primary_release_year: Int? = null
    ): LiveData<PagingData<DataMoviesModel.Movie>> {
        return dataRepository.getSearchResult(
            language,
            query,
            page,
            include_adult,
            region,
            year,
            primary_release_year
        )
    }

    suspend fun getAll(dataType: DataType): List<DataMovieDetailModel>? {
        return dataRepository.getStorageHandler(dataType).getAll()
    }

    suspend fun getById(dataType: DataType, movieId: Int): DataMovieDetailModel? {
        return dataRepository.getStorageHandler(dataType).getById(movieId)
    }

    suspend fun insert(dataType: DataType, movie: DataMovieDetailModel) {
        dataRepository.getStorageHandler(dataType).insert(movie)
    }

    suspend fun delete(dataType: DataType, movie: DataMovieDetailModel): Int {
        return dataRepository.getStorageHandler(dataType).delete(movie)
    }

    suspend fun updateMovie(dataType: DataType, movie: DataMovieDetailModel): Int {
        return dataRepository.getStorageHandler(dataType).updateMovie(movie)
    }

}