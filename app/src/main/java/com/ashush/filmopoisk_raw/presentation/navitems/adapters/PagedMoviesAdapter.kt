package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel

class PagedMoviesAdapter : PagingDataAdapter<DataMoviesModel.Movie, MovieViewHolder>(MOVIE_COMPARATOR) {

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<DataMoviesModel.Movie>() {
            override fun areItemsTheSame(oldItem: DataMoviesModel.Movie, newItem: DataMoviesModel.Movie): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: DataMoviesModel.Movie, newItem: DataMoviesModel.Movie): Boolean =
                oldItem == newItem
        }
    }

    lateinit var listener: IListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(getItem(position), listener)
    }
}