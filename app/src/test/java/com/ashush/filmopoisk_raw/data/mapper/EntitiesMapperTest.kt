package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import org.junit.Assert.*

class EntitiesMapperTest



//
//companion object {
//    fun mapToDetailMovie(entity: BaseEntity): DetailedMovie {
//        return DetailedMovie(
//            adult = entity.adult,
//            backdropPath = entity.backdropPath,
//            genres = entity.genres,
//            homepage = entity.homepage,
//            id = entity.id,
//            originalLanguage = entity.originalLanguage,
//            overview = entity.overview,
//            posterPath = entity.posterPath,
//            productionCompanies = entity.productionCompanies,
//            productionCountries = entity.productionCountries,
//            releaseDate = entity.releaseDate,
//            tagline = entity.tagline,
//            title = entity.title,
//            voteAverage = entity.voteAverage,
//        )
//    }
//
//    fun mapToFavorites(movie: DetailedMovie): Favorites {
//        return Favorites(
//            adult = movie.adult,
//            backdropPath = movie.backdropPath,
//            genres = movie.genres,
//            homepage = movie.homepage,
//            id = movie.id,
//            originalLanguage = movie.originalLanguage,
//            overview = movie.overview,
//            posterPath = movie.posterPath,
//            productionCompanies = movie.productionCompanies,
//            productionCountries = movie.productionCountries,
//            releaseDate = movie.releaseDate,
//            tagline = movie.tagline,
//            title = movie.title,
//            voteAverage = movie.voteAverage,
//        )
//    }
//
//    fun mapToWatchlist(movie: DetailedMovie): Watchlist {
//        return Watchlist(
//            adult = movie.adult,
//            backdropPath = movie.backdropPath,
//            genres = movie.genres,
//            homepage = movie.homepage,
//            id = movie.id,
//            originalLanguage = movie.originalLanguage,
//            overview = movie.overview,
//            posterPath = movie.posterPath,
//            productionCompanies = movie.productionCompanies,
//            productionCountries = movie.productionCountries,
//            releaseDate = movie.releaseDate,
//            tagline = movie.tagline,
//            title = movie.title,
//            voteAverage = movie.voteAverage,
//        )
//    }
//}
//}