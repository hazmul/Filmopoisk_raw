package com.ashush.filmopoisk_raw.models.data.configuration
import com.google.gson.annotations.SerializedName


data class DataGenresInfo(
    @SerializedName("genres")
    val genres: List<Genre?>?
) {
    data class Genre(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    )
}