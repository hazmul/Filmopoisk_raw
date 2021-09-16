package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.squareup.picasso.Picasso

class DetailedMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RecyclerItemBinding.bind(itemView)

    fun bindView(movieDomain: DomainDetailedMovie, listener: IListener?) {
        binding.cardViewTextTitle.text = movieDomain.title
        binding.cardViewTextOverView.text = when {
            movieDomain.overview.length >= 100 -> "${movieDomain.overview.substring(0, 100)}..."
            else -> movieDomain.overview
        }
        binding.cardViewTextYear.text = ("${"\\d{4}".toRegex().find(movieDomain.releaseDate)?.value}")
        binding.cardViewTextVote.text = movieDomain.voteAverage.toString()
        Picasso.get()
            .load(movieDomain.posterPath)
            .into(binding.cardViewImgPoster)
        binding.cardViewRoot.setOnClickListener { listener?.onClick(movieDomain.id) }
    }

}