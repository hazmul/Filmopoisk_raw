package com.ashush.filmopoisk_raw.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import retrofit2.Response

class MoviesListPagingSource(
    private val retrofitService: RetrofitService,
    private val parameters: MoviesParameters,
    private val requestType: MoviesListRequests
) : PagingSource<Int, DataMoviesModel.Movie>() {

    enum class MoviesListRequests { NowPlaying, Upcoming, TopRated, Popular }
    class MoviesParameters(
        val language: String?,
        val page: Int?,
        val region: String?
    )

    override fun getRefreshKey(state: PagingState<Int, DataMoviesModel.Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataMoviesModel.Movie> {
        val page = params.key ?: 1
        val result = try {
            val response: Response<DataMoviesModel>
            when (requestType) {
                MoviesListRequests.NowPlaying -> response = retrofitService.getMoviesNowPlaying(
                    language = parameters.language,
                    page = page,
                    region = parameters.region
                )
                MoviesListRequests.Upcoming
                -> response = retrofitService.getMoviesUpcoming(
                    language = parameters.language,
                    page = page,
                    region = parameters.region
                )
                MoviesListRequests.TopRated
                -> response = retrofitService.getMoviesTopRated(
                    language = parameters.language,
                    page = page,
                    region = parameters.region
                )
                MoviesListRequests.Popular
                -> response = retrofitService.getMoviesPopular(
                    language = parameters.language,
                    page = page,
                    region = parameters.region
                )
            }
            val pagedResponse = response.body()
            val data = pagedResponse?.movies!!
            val prevKey = null
            val currentPage = pagedResponse.page ?: 1
            val totalPages = pagedResponse.totalPages ?: 1
            val nextKey =
                if (currentPage < totalPages) {
                    currentPage + 1
                } else {
                    null
                }
            LoadResult.Page(
                data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        return result
    }
}

class MoviesSearchPagingSource(
    private val retrofitService: RetrofitService,
    private val parameters: MovieParameters
) : PagingSource<Int, DataMoviesModel.Movie>() {

    class MovieParameters(
        val language: String?,
        val query: String,
        val page: Int?,
        val includeAdult: Boolean?,
        val region: String?,
        val year: Int?,
        val primaryReleaseYear: Int?,
    )

    override fun getRefreshKey(state: PagingState<Int, DataMoviesModel.Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataMoviesModel.Movie> {
        val page = params.key ?: 1
        val result = try {
            val response: Response<DataMoviesModel> = retrofitService.getSearchResult(
                language = parameters.language,
                query = parameters.query,
                page = page,
                include_adult = parameters.includeAdult,
                region = parameters.region,
                year = parameters.year,
                primary_release_year = parameters.primaryReleaseYear,
            )
            val pagedResponse = response.body()
            val data = pagedResponse?.movies!!
            val prevKey = null
            val currentPage = pagedResponse.page ?: 1
            val totalPages = pagedResponse.totalPages ?: 1
            val nextKey =
                if (currentPage < totalPages) {
                    currentPage + 1
                } else {
                    null
                }
            LoadResult.Page(
                data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        return result
    }
}


