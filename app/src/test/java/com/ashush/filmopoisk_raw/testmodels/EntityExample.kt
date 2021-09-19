package com.ashush.filmopoisk_raw.testmodels

import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist

class EntityExample {
    companion object {

        private val posterLinkForTest = DataConfig.getBaseImageUrl("w780")
        private val backdropLinkForTest = DataConfig.getBaseImageUrl("w1280")

        val favorite1 = Favorites(
            adult = false,
            backdropPath = "${backdropLinkForTest}/4hBteCcQw7kahNtYApbf45f9Dy0.jpg",
            genres = "drama, horror",
            homepage = "",
            id = 5245,
            originalLanguage = "en",
            overview = "The film follows randy college boys Zack and Brian on a trip to Malibu for a party where there's a shortage of boys but a bevy of buxom girls. Beset on the way by car trouble, and various other distractions, the film also follows the journey of Zack and Brian's girlfriends, who follow the boys to Malibu but end up having their own lusty adventures.",
            posterPath = "${posterLinkForTest}/ilk4mLITsnR4JY8mpPIqPSgYrtg.jpg",
            productionCompanies = "Regency Enterprises",
            productionCountries = "United States of America",
            releaseDate = "2000-09-12",
            tagline = "Get ready for a wild, wild ride.",
            title = "Fast Lane to Malibu",
            voteAverage = 4.6,
        )
        val favorite2 = Favorites(
            adult = true,
            backdropPath = backdropLinkForTest,
            genres = "horror",
            homepage = "",
            id = 4205,
            originalLanguage = "en",
            overview = "During the summer of 1955, seventeen-year-old Eric Hansen embarks on a journey in his new town, a journey which will change his life forever.",
            posterPath = "${posterLinkForTest}/3wkKgbuv4jRSd9TxPQhbDrPoCwc.jpg",
            productionCompanies = "Knickerbocker Films, 20th Century Fox",
            productionCountries = "Russia",
            releaseDate = "1992-10-09",
            tagline = "One boy. One woman. And one night that changed their lives forever!",
            title = "Oh, What a Night",
            voteAverage = 5.1,
        )
        val watchlist1 = Watchlist(
            adult = false,
            backdropPath = "${backdropLinkForTest}/4hBteCcQw7kahNtYApbf45f9Dy0.jpg",
            genres = "drama, horror",
            homepage = "",
            id = 5245,
            originalLanguage = "en",
            overview = "The film follows randy college boys Zack and Brian on a trip to Malibu for a party where there's a shortage of boys but a bevy of buxom girls. Beset on the way by car trouble, and various other distractions, the film also follows the journey of Zack and Brian's girlfriends, who follow the boys to Malibu but end up having their own lusty adventures.",
            posterPath = "${posterLinkForTest}/ilk4mLITsnR4JY8mpPIqPSgYrtg.jpg",
            productionCompanies = "Regency Enterprises",
            productionCountries = "United States of America",
            releaseDate = "2000-09-12",
            tagline = "Get ready for a wild, wild ride.",
            title = "Fast Lane to Malibu",
            voteAverage = 4.6,
        )
        val watchlist2 = Watchlist(
            adult = true,
            backdropPath = backdropLinkForTest,
            genres = "horror",
            homepage = "",
            id = 4205,
            originalLanguage = "en",
            overview = "During the summer of 1955, seventeen-year-old Eric Hansen embarks on a journey in his new town, a journey which will change his life forever.",
            posterPath = "${posterLinkForTest}/3wkKgbuv4jRSd9TxPQhbDrPoCwc.jpg",
            productionCompanies = "Knickerbocker Films, 20th Century Fox",
            productionCountries = "Russia",
            releaseDate = "1992-10-09",
            tagline = "One boy. One woman. And one night that changed their lives forever!",
            title = "Oh, What a Night",
            voteAverage = 5.1,
        )
    }
}