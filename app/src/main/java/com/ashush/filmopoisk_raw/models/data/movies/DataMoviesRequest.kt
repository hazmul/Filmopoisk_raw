package com.ashush.filmopoisk_raw.models.data.movies

//request for get popular
//request for get top rated
//request for get now playing
//request for get upcoming

data class DataMoviesRequest(
    val type: String = "GET",
    val api_key: String,
    val language: String?,
    val page: String?,
    val region: String?
)