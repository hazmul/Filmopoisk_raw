package com.ashush.filmopoisk_raw.testmodels

import com.ashush.filmopoisk_raw.data.models.movies.DataDetailedMovie

class DataDetailedMoviesExamples {
    companion object {
        val dataDetailMovie1 =
            DataDetailedMovie(
                adult = false,
                backdropPath = "/4hBteCcQw7kahNtYApbf45f9Dy0.jpg",
                belongsToCollection = null,
                budget = 0,
                genres = listOf(
                    GenreExamples.genre1,
                    GenreExamples.genre2
                ),
                homepage = "",
                id = 5245,
                imdbId = "tt0240500",
                originalLanguage = "en",
                originalTitle = "Fast Lane to Malibu",
                overview = "The film follows randy college boys Zack and Brian on a trip to Malibu for a party where there's a shortage of boys but a bevy of buxom girls. Beset on the way by car trouble, and various other distractions, the film also follows the journey of Zack and Brian's girlfriends, who follow the boys to Malibu but end up having their own lusty adventures.",
                popularity = 3.601,
                posterPath = "/ilk4mLITsnR4JY8mpPIqPSgYrtg.jpg",
                productionCompanies = listOf(
                    ProductionCompanyExamples.company1
                ),
                productionCountries = listOf(
                    ProductionCountryExamples.country1
                ),
                releaseDate = "2000-09-12",
                revenue = 0,
                runtime = 80,
                spokenLanguages = listOf(
                    SpokenLanguageExamples.language1
                ),
                status = "Released",
                tagline = "Get ready for a wild, wild ride.",
                title = "Fast Lane to Malibu",
                video = false,
                voteAverage = 4.6,
                voteCount = 11,
            )
        val dataDetailMovie2 =
            DataDetailedMovie(
                adult = false,
                backdropPath = "/mCVQ2cZmGkAHG2Q3fDZTQA1YzeI.jpg",
                belongsToCollection = Any(),
                budget = 59000000,
                genres = listOf(
                    GenreExamples.genre1,
                    GenreExamples.genre3
                ),
                homepage = "http://www.iceagemovies.com/films/ice-age",
                id = 425,
                imdbId = "tt0268380",
                originalLanguage = "en",
                originalTitle = "Ice Age",
                overview = "With the impending ice age almost upon them, a mismatched trio of prehistoric critters – Manny the woolly mammoth, Diego the saber-toothed tiger and Sid the giant sloth – find an orphaned infant and decide to return it to its human parents. Along the way, the unlikely allies become friends but, when enemies attack, their quest takes on far nobler aims.",
                popularity = 109.572,
                posterPath = "/gLhHHZUzeseRXShoDyC4VqLgsNv.jpg",
                productionCompanies = listOf(
                    ProductionCompanyExamples.company1,
                    ProductionCompanyExamples.company2,
                    ProductionCompanyExamples.company3,
                ),
                productionCountries = listOf(
                    ProductionCountryExamples.country1,
                    ProductionCountryExamples.country2
                ),
                releaseDate = "2002-03-10",
                revenue = 383257136,
                runtime = 81,
                spokenLanguages = listOf(
                    SpokenLanguageExamples.language1,
                    SpokenLanguageExamples.language2
                ),
                status = "Released",
                tagline = "They came. They thawed. They conquered.",
                title = "Ice Age",
                video = false,
                voteAverage = 7.3,
                voteCount = 10354,
            )
        val dataDetailMovie3 =
            DataDetailedMovie(
                adult = true,
                backdropPath = null,
                belongsToCollection = null,
                budget = 0,
                genres = listOf(
                    GenreExamples.genre2
                ),
                homepage = "",
                id = 4205,
                imdbId = "tt0105048",
                originalLanguage = "en",
                originalTitle = "Oh, What a Night",
                overview = "During the summer of 1955, seventeen-year-old Eric Hansen embarks on a journey in his new town, a journey which will change his life forever.",
                popularity = 2.383,
                posterPath = "/3wkKgbuv4jRSd9TxPQhbDrPoCwc.jpg",
                productionCompanies = listOf(
                    ProductionCompanyExamples.company2,
                    ProductionCompanyExamples.company3,
                ),
                productionCountries = listOf(
                    ProductionCountryExamples.country3
                ),
                releaseDate = "1992-10-09",
                revenue = 0,
                runtime = 93,
                spokenLanguages = listOf(
                    SpokenLanguageExamples.language3
                ),
                status = "Released",
                tagline = "One boy. One woman. And one night that changed their lives forever!",
                title = "Oh, What a Night",
                video = false,
                voteAverage = 5.1,
                voteCount = 7,
            )
    }

    class GenreExamples {
        companion object {
            val genre1 = DataDetailedMovie.Genre(
                id = 18,
                name = "Drama",
            )
            val genre2 = DataDetailedMovie.Genre(
                id = 27,
                name = "Horror",
            )

            val genre3 = DataDetailedMovie.Genre(
                id = 35,
                name = "Comedy",
            )
        }
    }

    class ProductionCompanyExamples {
        companion object {
            val company1 = DataDetailedMovie.ProductionCompany(
                id = 508,
                logoPath = "/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png",
                name = "Regency Enterprises",
                originCountry = "US",
            )
            val company2 = DataDetailedMovie.ProductionCompany(
                id = 54052,
                logoPath = null,
                name = "Knickerbocker Films",
                originCountry = "",
            )
            val company3 = DataDetailedMovie.ProductionCompany(
                id = 25,
                logoPath = "/qZCc1lty5FzX30aOCVRBLzaVmcp.png",
                name = "20th Century Fox",
                originCountry = "US",
            )
        }
    }

    class ProductionCountryExamples {
        companion object {
            val country1 = DataDetailedMovie.ProductionCountry(
                iso31661 = "US",
                name = "United States of America",
            )
            val country2 = DataDetailedMovie.ProductionCountry(
                iso31661 = "UA",
                name = "Ukraine",
            )
            val country3 = DataDetailedMovie.ProductionCountry(
                iso31661 = "RU",
                name = "Russia",
            )
        }
    }

    class SpokenLanguageExamples {
        companion object {
            val language1 = DataDetailedMovie.SpokenLanguage(
                iso6391 = "en",
                name = "English",
            )
            val language2 = DataDetailedMovie.SpokenLanguage(
                iso6391 = "fr",
                name = "French",
            )
            val language3 = DataDetailedMovie.SpokenLanguage(
                iso6391 = "ru",
                name = "Russian",
            )
        }
    }
}
