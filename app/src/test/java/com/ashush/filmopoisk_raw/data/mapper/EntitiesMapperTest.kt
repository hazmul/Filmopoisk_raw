package com.ashush.filmopoisk_raw.data.mapper

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.testmodels.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EntitiesMapperTest {
    @Before
    fun init() {
        DataConfig.genres = DataGenresInfoExample.value
        DataConfig.config = DataConfigurationModelExample.value
    }

    @Test
    fun mapToDetailMovieCase1() {
        val expectedResult = DomainDetailedMovieExample.domainDetailMovie1
        val actualResult = EntitiesMapper.mapToDetailMovie(EntityExample.favorite1)
        Assert.assertEquals(expectedResult, actualResult)
    }
    @Test
    fun mapToDetailMovieCase2() {
        val expectedResult = DomainDetailedMovieExample.domainDetailMovie1
        val actualResult = EntitiesMapper.mapToDetailMovie(EntityExample.watchlist1)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun mapToFavorites1() {
        val expectedResult = EntityExample.favorite1
        val actualResult = EntitiesMapper.mapToFavorites(DomainDetailedMovieExample.domainDetailMovie1)
        Assert.assertEquals(expectedResult, actualResult)
    }
    @Test
    fun mapToFavorites2() {
        val expectedResult = EntityExample.favorite2
        val actualResult = EntitiesMapper.mapToFavorites(DomainDetailedMovieExample.domainDetailMovie3)
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun mapToWatchlist1() {
        val expectedResult = EntityExample.watchlist1
        val actualResult = EntitiesMapper.mapToWatchlist(DomainDetailedMovieExample.domainDetailMovie1)
        Assert.assertEquals(expectedResult, actualResult)
    }
    @Test
    fun mapToWatchlist2() {
        val expectedResult = EntityExample.watchlist2
        val actualResult = EntitiesMapper.mapToWatchlist(DomainDetailedMovieExample.domainDetailMovie3)
        Assert.assertEquals(expectedResult, actualResult)
    }

}



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