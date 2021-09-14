package com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter.adapter

fun interface GenreItemOnClickListener {
    fun genreOnClick(genreId: Int, newState: Boolean)
}