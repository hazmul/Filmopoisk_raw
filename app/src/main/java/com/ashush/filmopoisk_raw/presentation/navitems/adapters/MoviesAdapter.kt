package com.ashush.filmopoisk_raw.presentation.navitems.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.ashush.filmopoisk_raw.domain.models.DomainMovies

/**
 * Адаптер для отображения списка фильмов
 * Использует модель данных [DomainMovies]
 */

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    private var domainMovies: List<DomainMovies.Movie> = emptyList()

    lateinit var listener: IListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MoviesViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindView(domainMovies[position], listener)
    }

    override fun getItemCount(): Int {
        return domainMovies.size
    }

    fun update(newDomainMovies: List<DomainMovies.Movie>) {
        val diffResult = DiffUtil.calculateDiff(object : MovieDiffUtil<DomainMovies.Movie>(domainMovies, newDomainMovies) {})
        diffResult.dispatchUpdatesTo(this)
        this.domainMovies = newDomainMovies
    }

}