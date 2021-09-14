package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import androidx.recyclerview.widget.DiffUtil

open class MovieDiffUtil<T>(
    private var oldMovies: List<T>,
    private var newMovies: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldMovies.size
    }

    override fun getNewListSize(): Int {
        return newMovies.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition] == newMovies[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovies[oldItemPosition] == (newMovies[newItemPosition])
    }

}