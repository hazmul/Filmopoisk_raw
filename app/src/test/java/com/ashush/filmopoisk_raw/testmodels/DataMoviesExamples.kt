package com.ashush.filmopoisk_raw.testmodels

import com.ashush.filmopoisk_raw.data.models.movies.DataMovies

class DataMoviesExamples {
    companion object {
        val dataMoviesModel1 = DataMovies(
            page = 1,
            movies = listOf(
                DataMovieExample.dataMovie1,
                DataMovieExample.dataMovie2,
                DataMovieExample.dataMovie3,
            ),
            totalPages = 123,
            totalResults = 41242,
        )
        val dataMoviesModel2 = DataMovies(
            page = 3,
            movies = listOf(
                DataMovieExample.dataMovie1,
                DataMovieExample.dataMovie3,
            ),
            totalPages = 1232,
            totalResults = 53252,
        )
        val dataMoviesModel3 = DataMovies(
            page = 1,
            movies = listOf(
                DataMovieExample.dataMovie2,
            ),
            totalPages = 1,
            totalResults = 20,
        )
    }

    class DataMovieExample {
        companion object {
            val dataMovie1 = DataMovies.Movie(
                adult = false,
                backdropPath = "/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
                genreIds = listOf(14, 28, 80),
                id = 297761,
                originalLanguage = "en",
                originalTitle = "Suicide Squad",
                overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                popularity = 48.261451,
                posterPath = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
                releaseDate = "2016-08-03",
                title = "Suicide Squad",
                video = false,
                voteAverage = 5.91,
                voteCount = 1466,
            )
            val dataMovie2 = DataMovies.Movie(
                adult = false,
                backdropPath = "/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg",
                genreIds = listOf(28, 53),
                id = 324668,
                originalLanguage = "en",
                originalTitle = "Jason Bourne",
                overview = "The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about his past.",
                popularity = 30.690177,
                posterPath = "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
                releaseDate = "2016-07-27",
                title = "Jason Bourne",
                video = false,
                voteAverage = 5.25,
                voteCount = 649,
            )
            val dataMovie3 = DataMovies.Movie(
                adult = false,
                backdropPath = "/zrAO2OOa6s6dQMQ7zsUbDyIBrAP.jpg",
                genreIds = listOf(28, 12, 35, 80, 9648, 53),
                id = 291805,
                originalLanguage = "en",
                originalTitle = "Now You See Me 2",
                overview = "One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet.",
                popularity = 29.737342,
                posterPath = "/hU0E130tsGdsYa4K9lc3Xrn5Wyt.jpg",
                releaseDate = "2016-06-02",
                title = "Now You See Me 2",
                video = false,
                voteAverage = 6.64,
                voteCount = 684,
            )
        }
    }


}
