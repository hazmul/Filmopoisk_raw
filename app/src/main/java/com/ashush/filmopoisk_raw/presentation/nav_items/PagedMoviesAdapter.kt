package com.ashush.filmopoisk_raw.presentation.nav_items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.squareup.picasso.Picasso

class PagedMoviesAdapter :
    PagingDataAdapter<DataMoviesModel.Movie, PagedMoviesAdapter.ViewHolder>(MOVIE_COMPARATOR) {

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<DataMoviesModel.Movie>() {
            override fun areItemsTheSame(oldItem: DataMoviesModel.Movie, newItem: DataMoviesModel.Movie): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: DataMoviesModel.Movie, newItem: DataMoviesModel.Movie): Boolean =
                oldItem == newItem
        }
    }

    lateinit var listener: IListener

    interface IListener {
        fun onClick(movieId: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerItemBinding.bind(itemView)

        fun bindView(movie: DataMoviesModel.Movie?, listener: IListener?) {
            binding.cardViewTextView.text = movie?.title
            Picasso.get()
                .load(DataConfig.getBasePosterUrl(DataConfig.config?.images?.posterSizes?.firstOrNull()) + movie?.posterPath)
                .into(binding.cardViewIconImageView)
            binding.cardViewRoot.setOnClickListener { listener?.onClick(movie?.id!!) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position), listener)
    }

}