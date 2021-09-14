package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.ashush.filmopoisk_raw.domain.models.Movies

class DetailedMoviesAdapter : RecyclerView.Adapter<DetailedMoviesViewHolder>() {

    private var movies: List<DetailedMovie> = emptyList()

    lateinit var listener: IListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetailedMoviesViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: DetailedMoviesViewHolder, position: Int) {
        holder.bindView(movies[position], listener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun update(newMovies: List<DetailedMovie>) {
        val diffResult = DiffUtil.calculateDiff(object : MovieDiffUtil<DetailedMovie>(movies, newMovies) {})
        diffResult.dispatchUpdatesTo(this)
        this.movies = newMovies
    }
}