package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel

class MapperDB {
    companion object {
        fun mapToDataMovieDetail(entity: BaseEntity) : DataMovieDetailModel {
            return DataMovieDetailModel(
                id = entity.movieId,
                title = entity.title,
                popularity = entity.popularity,
                releaseDate = entity.releaseDate,
                overview = entity.overview,
                tagline = entity.tagline,
                posterPath = entity.posterPath
            )
        }
        fun  mapToFavorites(movie: DataMovieDetailModel) : Favorites {
            return Favorites(
                rowid = 0,
                movieId = movie.id,
                title = movie.title,
                popularity = movie.popularity,
                releaseDate = movie.releaseDate,
                overview = movie.overview,
                tagline = movie.tagline,
                posterPath = movie.posterPath
            )
        }
        fun mapToWatchlist(movie: DataMovieDetailModel) : Watchlist {
            return Watchlist(
                rowid = 0,
                movieId = movie.id,
                title = movie.title,
                popularity = movie.popularity,
                releaseDate = movie.releaseDate,
                overview = movie.overview,
                tagline = movie.tagline,
                posterPath = movie.posterPath
            )
        }
    }
}