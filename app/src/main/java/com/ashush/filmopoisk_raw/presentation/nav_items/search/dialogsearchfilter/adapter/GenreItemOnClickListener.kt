package com.ashush.filmopoisk_raw.presentation.nav_items.search.dialogsearchfilter.adapter

fun interface GenreItemOnClickListener {
    fun genreOnClick(genreId: Int, newState: Boolean)
}