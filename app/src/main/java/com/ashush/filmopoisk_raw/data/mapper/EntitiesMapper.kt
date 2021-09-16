package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie

class EntitiesMapper {
    companion object {
        fun mapToDetailMovie(entity: BaseEntity): DomainDetailedMovie {
            return DomainDetailedMovie(
                adult = entity.adult,
                backdropPath = entity.backdropPath,
                genres = entity.genres,
                homepage = entity.homepage,
                id = entity.id,
                originalLanguage = entity.originalLanguage,
                overview = entity.overview,
                posterPath = entity.posterPath,
                productionCompanies = entity.productionCompanies,
                productionCountries = entity.productionCountries,
                releaseDate = entity.releaseDate,
                tagline = entity.tagline,
                title = entity.title,
                voteAverage = entity.voteAverage,
            )
        }

        fun mapToFavorites(movieDomain: DomainDetailedMovie): Favorites {
            return Favorites(
                adult = movieDomain.adult,
                backdropPath = movieDomain.backdropPath,
                genres = movieDomain.genres,
                homepage = movieDomain.homepage,
                id = movieDomain.id,
                originalLanguage = movieDomain.originalLanguage,
                overview = movieDomain.overview,
                posterPath = movieDomain.posterPath,
                productionCompanies = movieDomain.productionCompanies,
                productionCountries = movieDomain.productionCountries,
                releaseDate = movieDomain.releaseDate,
                tagline = movieDomain.tagline,
                title = movieDomain.title,
                voteAverage = movieDomain.voteAverage,
            )
        }

        fun mapToWatchlist(movieDomain: DomainDetailedMovie): Watchlist {
            return Watchlist(
                adult = movieDomain.adult,
                backdropPath = movieDomain.backdropPath,
                genres = movieDomain.genres,
                homepage = movieDomain.homepage,
                id = movieDomain.id,
                originalLanguage = movieDomain.originalLanguage,
                overview = movieDomain.overview,
                posterPath = movieDomain.posterPath,
                productionCompanies = movieDomain.productionCompanies,
                productionCountries = movieDomain.productionCountries,
                releaseDate = movieDomain.releaseDate,
                tagline = movieDomain.tagline,
                title = movieDomain.title,
                voteAverage = movieDomain.voteAverage,
            )
        }
    }
}