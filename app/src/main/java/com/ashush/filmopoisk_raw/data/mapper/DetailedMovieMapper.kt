package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.models.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import java.util.*

class DetailedMovieMapper {
    companion object {
        fun mapToDetailMovie(dataMovie: DataMovieDetailModel): DetailedMovie {
            return DetailedMovie(
                adult = dataMovie.adult ?: false,
                backdropPath = DataConfig.getBaseImageUrl() + dataMovie.backdropPath,
                genres = if (dataMovie.genres?.isNotEmpty() == true) {
                    dataMovie.genres.map { it?.name }.reduce { str, item -> "$str, $item" }
                        ?.lowercase(Locale.getDefault()) ?: ""
                } else {
                    ""
                },
                homepage = dataMovie.homepage ?: "",
                id = dataMovie.id ?: 0,
                originalLanguage = dataMovie.originalLanguage ?: "",
                overview = dataMovie.overview ?: "",
                posterPath = DataConfig.getBaseImageUrl() + dataMovie.posterPath,
                productionCompanies = if (dataMovie.productionCompanies?.isNotEmpty() == true) {
                    dataMovie.productionCompanies.map { it?.name }.reduce { str, item -> "$str, $item" } ?: ""
                } else {
                    ""
                },
                productionCountries = if (dataMovie.productionCountries?.isNotEmpty() == true) {
                    dataMovie.productionCountries.map { it?.name }.reduce { str, item -> "$str, $item" } ?: ""
                } else {
                    ""
                },
                releaseDate = dataMovie.releaseDate ?: "",
                tagline = dataMovie.tagline ?: "",
                title = dataMovie.title ?: "false",
                voteAverage = dataMovie.voteAverage ?: 0.0,
            )
        }
    }
}