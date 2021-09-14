package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RecyclerItemBinding.bind(itemView)

    fun bindView(movie: DataMoviesModel.Movie?, listener: IListener?) {
        binding.cardViewTextTitle.text = movie?.title
        binding.cardViewTextOverView.text = when {
            movie?.overview?.length!! >= 100 -> "${movie.overview.substring(0, 100)}..."
            else -> movie.overview
        }
        binding.cardViewTextYear.text = ("(${"\\d{4}".toRegex().find(movie.releaseDate!!)?.value})")
        binding.cardViewTextVote.text = movie.voteAverage.toString()
        Picasso.get()
            .load(DataConfig.getBasePosterUrl(null) + movie.posterPath)
            .into(binding.cardViewImgPoster)
        binding.cardViewRoot.setOnClickListener { listener?.onClick(movie.id!!) }
    }
}