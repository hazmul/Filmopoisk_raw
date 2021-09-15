package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.models.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.domain.models.Movies
import java.util.*

class MoviesMapper {
    companion object {
        fun mapToMovies(dataMovies: DataMoviesModel): Movies {
            val list = mutableListOf<Movies.Movie>()
            dataMovies.movies?.let {
                for (movie in dataMovies.movies) {
                    list.add(
                        Movies.Movie(
                            adult = movie.adult ?: false,
                            posterPath = DataConfig.getBaseImageUrl(DataConfig.config?.images?.backdropSizes?.firstOrNull()) + movie.posterPath,
                            genres = if (movie.genreIds?.isNotEmpty() == true) {
                                DataConfig.genres?.genres?.filter { it?.id in movie.genreIds }?.map { it?.name }
                                    ?.reduce { str, item -> "$str, $item" }
                                    ?.lowercase(Locale.getDefault()) ?: ""
                            } else {
                                ""
                            },
                            genresId = if (movie.genreIds?.isNotEmpty() == true) {
                                movie.genreIds
                            } else {
                                listOf(0)
                            },
                            id = movie.id ?: 0,
                            overview = movie.overview ?: "",
                            releaseDate = movie.releaseDate ?: "",
                            title = movie.title ?: "false",
                            voteAverage = movie.voteAverage ?: 0.0,
                        )
                    )
                }
            }
            return Movies(
                currentPage = dataMovies.page ?: 1,
                totalPages = dataMovies.totalPages ?: 1,
                moviesList = list
            )
        }
    }
}