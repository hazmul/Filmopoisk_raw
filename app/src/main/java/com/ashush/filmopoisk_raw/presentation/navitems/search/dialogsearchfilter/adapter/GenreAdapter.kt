package com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R

class GenreAdapter(private var genres: List<GenreItem>,
                   private val clickListener: GenreItemOnClickListener
) : RecyclerView.Adapter<GenreViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(genres: List<GenreItem>,) {
        this.genres = genres
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_search_filter_genre, parent, false
        )
        return GenreViewHolder(itemView)
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position], clickListener)
    }
}
