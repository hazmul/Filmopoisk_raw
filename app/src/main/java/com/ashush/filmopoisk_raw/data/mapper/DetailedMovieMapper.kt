package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.models.movies.DataDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import java.util.*

/**
 * В этом классе преобразуюся модели data слоя в модели domain слоя
 */

class DetailedMovieMapper {
    companion object {
        /**
         * Функция преобразования
         * Получает [DataDetailedMovie]
         * @return [DomainDetailedMovie]
         */
        fun mapToDomainDetailedMovie(dataDetailedMovie: DataDetailedMovie): DomainDetailedMovie {
            return DomainDetailedMovie(
                adult = dataDetailedMovie.adult ?: false,
                backdropPath = DataConfig.getBaseImageUrl() + (dataDetailedMovie.backdropPath ?: ""),
                genres = if (dataDetailedMovie.genres?.isNotEmpty() == true) {
                    dataDetailedMovie.genres.map { it?.name }.reduce { str, item -> "$str, $item" }
                        ?.lowercase(Locale.getDefault()) ?: ""
                } else {
                    ""
                },
                homepage = dataDetailedMovie.homepage ?: "",
                id = dataDetailedMovie.id ?: 0,
                originalLanguage = dataDetailedMovie.originalLanguage ?: "",
                overview = dataDetailedMovie.overview ?: "",
                posterPath = DataConfig.getBaseImageUrl() + (dataDetailedMovie.posterPath ?: ""),
                productionCompanies = if (dataDetailedMovie.productionCompanies?.isNotEmpty() == true) {
                    dataDetailedMovie.productionCompanies.map { it?.name }.reduce { str, item -> "$str, $item" } ?: ""
                } else {
                    ""
                },
                productionCountries = if (dataDetailedMovie.productionCountries?.isNotEmpty() == true) {
                    dataDetailedMovie.productionCountries.map { it?.name }.reduce { str, item -> "$str, $item" } ?: ""
                } else {
                    ""
                },
                releaseDate = dataDetailedMovie.releaseDate ?: "",
                tagline = dataDetailedMovie.tagline ?: "",
                title = dataDetailedMovie.title ?: "false",
                voteAverage = dataDetailedMovie.voteAverage ?: 0.0,
            )
        }
    }
}