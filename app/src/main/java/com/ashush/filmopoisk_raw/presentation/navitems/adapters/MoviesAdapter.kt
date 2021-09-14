package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.squareup.picasso.Picasso

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<DataMoviesModel.Movie?> = emptyList()

    lateinit var listener: IListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies[position]?.let { holder.bindView(it, listener) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(movies: List<DataMoviesModel.Movie?>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}