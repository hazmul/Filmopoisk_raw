package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie

/**
 * Адаптер для отображения списка фильмов в категориях "Favorites" и "Watchlist"
 * Использует модель данных [DomainDetailedMovie]
 */

class DetailedMoviesAdapter : RecyclerView.Adapter<DetailedMoviesViewHolder>() {

    private var movieDomains: List<DomainDetailedMovie> = emptyList()

    lateinit var listener: IListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DetailedMoviesViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: DetailedMoviesViewHolder, position: Int) {
        holder.bindView(movieDomains[position], listener)
    }

    override fun getItemCount(): Int {
        return movieDomains.size
    }

    fun update(newMovieDomains: List<DomainDetailedMovie>) {
        val diffResult = DiffUtil.calculateDiff(object : MovieDiffUtil<DomainDetailedMovie>(movieDomains, newMovieDomains) {})
        diffResult.dispatchUpdatesTo(this)
        this.movieDomains = newMovieDomains
    }
}