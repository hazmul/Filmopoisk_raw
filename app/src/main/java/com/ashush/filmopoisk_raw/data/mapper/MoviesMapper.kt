package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.models.movies.DataMovies
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import java.util.*

/**
 * В этом классе преобразуюся модели data слоя в модели domain слоя
 */

class MoviesMapper {
    companion object {
        /**
         * Функция преобразования
         * Получает [DataMovies]
         * @return [DomainMovies]
         */
        fun mapToDomainMovies(dataMovies: DataMovies): DomainMovies {
            val list = mutableListOf<DomainMovies.Movie>()
            dataMovies.movies?.let {
                for (movie in dataMovies.movies) {
                    list.add(
                        DomainMovies.Movie(
                            adult = movie.adult ?: false,
                            posterPath = DataConfig.getBaseImageUrl() + (movie.posterPath ?: ""),
                            genres = if (movie.genreIds?.isNotEmpty() == true) {
                                DataConfig.genres?.genres?.filter { it?.id in movie.genreIds }?.map { it?.name }
                                    ?.reduce { str, item -> "$str, $item" }
                                    ?.lowercase(Locale.getDefault()) ?: ""
                            } else {
                                ""
                            },
                            genreIds = if (movie.genreIds?.isNotEmpty() == true) {
                                movie.genreIds
                            } else {
                                listOf(0)
                            },
                            id = movie.id ?: 0,
                            overview = movie.overview ?: "",
                            releaseDate = movie.releaseDate ?: "",
                            title = movie.title ?: "",
                            voteAverage = movie.voteAverage ?: 0.0,
                        )
                    )
                }
            }
            return DomainMovies(
                currentPage = dataMovies.page ?: 1,
                totalPages = dataMovies.totalPages ?: 1,
                moviesList = list
            )
        }
    }
}