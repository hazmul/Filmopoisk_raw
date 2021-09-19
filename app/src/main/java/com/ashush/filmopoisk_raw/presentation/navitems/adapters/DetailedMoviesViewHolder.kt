package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.squareup.picasso.Picasso

/**
 * ViewHolder для соотсветсвующего адаптера [DetailedMoviesAdapter]
 */

class DetailedMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RecyclerItemBinding.bind(itemView)

    fun bindView(movieDomain: DomainDetailedMovie, listener: IListener?) {
        binding.cardViewTextTitle.text = movieDomain.title
        binding.cardViewTextGenres.text = movieDomain.genres
        binding.cardViewTextYear.text = ("${"\\d{4}".toRegex().find(movieDomain.releaseDate)?.value}")
        binding.cardViewTextVote.text = movieDomain.voteAverage.toString()
        Picasso.get()
            .load(movieDomain.posterPath)
            .into(binding.cardViewImgPoster)
        binding.cardViewRoot.setOnClickListener { listener?.onClick(movieDomain.id) }
    }

}