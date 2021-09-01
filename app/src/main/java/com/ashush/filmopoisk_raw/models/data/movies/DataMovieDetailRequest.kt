package com.ashush.filmopoisk_raw.models.data.movies

data class DataMovieDetailRequest(
    val type: String = "GET",
    val id: Int,
    val api_key: String,
    val language: String?,
    val append_to_response: String?
)