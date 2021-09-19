package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.domain.models.DomainMovies
import com.squareup.picasso.Picasso

/**
 * ViewHolder для соотсветсвующего адаптера [MoviesAdapter]
 */

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RecyclerItemBinding.bind(itemView)

    fun bindView(movie: DomainMovies.Movie, listener: IListener?) {
        binding.cardViewTextTitle.text = movie.title
        binding.cardViewTextGenres.text = movie.genres
        binding.cardViewTextYear.text = ("${"\\d{4}".toRegex().find(movie.releaseDate)?.value}")
        binding.cardViewTextVote.text = movie.voteAverage.toString()
        Picasso.get()
            .load(movie.posterPath)
            .into(binding.cardViewImgPoster)
        binding.cardViewRoot.setOnClickListener { listener?.onClick(movie.id) }
    }
}