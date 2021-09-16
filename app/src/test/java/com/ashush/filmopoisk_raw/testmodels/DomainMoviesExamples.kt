package com.ashush.filmopoisk_raw.testmodels


import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.domain.models.DomainMovies

class DomainMoviesExamples {
    companion object {
        val moviesModel1 =
            DomainMovies(
                currentPage = 1,
                totalPages = 123,
                moviesList = listOf(
                    DomainMovieExample.moviesMovie1,
                    DomainMovieExample.moviesMovie2,
                    DomainMovieExample.moviesMovie3,
                )
            )
        val moviesModel2 =
            DomainMovies(
                currentPage = 3,
                totalPages = 1232,
                moviesList = listOf(
                    DomainMovieExample.moviesMovie1,
                    DomainMovieExample.moviesMovie3,
                )
            )
        val moviesModel3 =
            DomainMovies(
                currentPage = 1,
                totalPages = 1,
                moviesList = listOf(
                    DomainMovieExample.moviesMovie2,
                )
            )
    }
    class DomainMovieExample {
        companion object {

            private val imagesLinkForTest = DataConfig.getBaseImageUrl()

            val moviesMovie1 =
                DomainMovies.Movie(
                    adult = false,
                    genreIds = listOf(14, 28, 80),
                    genres = "action, crime, fantasy",
                    id = 297761,
                    overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                    posterPath = "${imagesLinkForTest}/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
                    releaseDate = "2016-08-03",
                    title = "Suicide Squad",
                    voteAverage = 5.91,
                )
            val moviesMovie2 =
                DomainMovies.Movie(
                    adult = false,
                    genreIds = listOf(28, 53),
                    genres = "action, thriller",
                    id = 324668,
                    overview = "The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about his past.",
                    posterPath = "${imagesLinkForTest}/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
                    releaseDate = "2016-07-27",
                    title = "Jason Bourne",
                    voteAverage = 5.25,
                )
            val moviesMovie3 =
                DomainMovies.Movie(
                    adult = false,
                    genreIds = listOf(28, 12, 35, 80, 9648, 53),
                    genres = "action, adventure, comedy, crime, mystery, thriller",
                    id = 291805,
                    overview = "One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet.",
                    posterPath = "${imagesLinkForTest}/hU0E130tsGdsYa4K9lc3Xrn5Wyt.jpg",
                    releaseDate = "2016-06-02",
                    title = "Now You See Me 2",
                    voteAverage = 6.64,
                )
        }
    }
}



