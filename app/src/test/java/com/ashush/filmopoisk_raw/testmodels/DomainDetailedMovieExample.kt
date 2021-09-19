package com.ashush.filmopoisk_raw.testmodels

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie

class DomainDetailedMovieExample {
    companion object {

        private val posterLinkForTest = DataConfig.getBaseImageUrl("w780")
        private val backdropLinkForTest = DataConfig.getBaseImageUrl("w1280")

        val domainDetailMovie1 =
            DomainDetailedMovie(
                adult = false,
                backdropPath = "$backdropLinkForTest/4hBteCcQw7kahNtYApbf45f9Dy0.jpg",
                genres = "drama, horror",
                homepage = "",
                id = 5245,
                originalLanguage = "en",
                overview = "The film follows randy college boys Zack and Brian on a trip to Malibu for a party where there's a shortage of boys but a bevy of buxom girls. Beset on the way by car trouble, and various other distractions, the film also follows the journey of Zack and Brian's girlfriends, who follow the boys to Malibu but end up having their own lusty adventures.",
                posterPath = "$posterLinkForTest/ilk4mLITsnR4JY8mpPIqPSgYrtg.jpg",
                productionCompanies = "Regency Enterprises",
                productionCountries = "United States of America",
                releaseDate = "2000-09-12",
                tagline = "Get ready for a wild, wild ride.",
                title = "Fast Lane to Malibu",
                voteAverage = 4.6,
            )
        val domainDetailMovie2 =
            DomainDetailedMovie(
                adult = false,
                backdropPath = "$backdropLinkForTest/mCVQ2cZmGkAHG2Q3fDZTQA1YzeI.jpg",
                genres = "drama, comedy",
                homepage = "http://www.iceagemovies.com/films/ice-age",
                id = 425,
                originalLanguage = "en",
                overview = "With the impending ice age almost upon them, a mismatched trio of prehistoric critters – Manny the woolly mammoth, Diego the saber-toothed tiger and Sid the giant sloth – find an orphaned infant and decide to return it to its human parents. Along the way, the unlikely allies become friends but, when enemies attack, their quest takes on far nobler aims.",
                posterPath = "$posterLinkForTest/gLhHHZUzeseRXShoDyC4VqLgsNv.jpg",
                productionCompanies = "Regency Enterprises, Knickerbocker Films, 20th Century Fox",
                productionCountries = "United States of America, Ukraine",
                releaseDate = "2002-03-10",
                tagline = "They came. They thawed. They conquered.",
                title = "Ice Age",
                voteAverage = 7.3,
            )
        val domainDetailMovie3 =
            DomainDetailedMovie(
                adult = true,
                backdropPath = backdropLinkForTest,
                genres = "horror",
                homepage = "",
                id = 4205,
                originalLanguage = "en",
                overview = "During the summer of 1955, seventeen-year-old Eric Hansen embarks on a journey in his new town, a journey which will change his life forever.",
                posterPath = "$posterLinkForTest/3wkKgbuv4jRSd9TxPQhbDrPoCwc.jpg",
                productionCompanies = "Knickerbocker Films, 20th Century Fox",
                productionCountries = "Russia",
                releaseDate = "1992-10-09",
                tagline = "One boy. One woman. And one night that changed their lives forever!",
                title = "Oh, What a Night",
                voteAverage = 5.1,
            )
    }
}