package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.domain.models.Movies

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies: List<Movies.Movie> = emptyList()

    lateinit var listener: IListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MoviesViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindView(movies[position], listener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun update(newMovies: List<Movies.Movie>) {
        val diffResult = DiffUtil.calculateDiff(object : MovieDiffUtil<Movies.Movie>(movies, newMovies) {})
        diffResult.dispatchUpdatesTo(this)
        this.movies = newMovies
    }

}