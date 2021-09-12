package com.ashush.filmopoisk_raw.presentation.nav_items.search.dialogsearchfilter.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.databinding.ItemSearchFilterGenreBinding

class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewBinding = ItemSearchFilterGenreBinding.bind(itemView)

    fun bind(item: GenreItem, onClickListener: GenreItemOnClickListener) = with(viewBinding) {
        itemSearchFilterGenreChecked.isChecked = item.isChecked
        itemSearchFilterGenreName.text = item.genre?.name.orEmpty()
        itemSearchFilterGenreChecked.setOnClickListener {
            onClickListener.genreOnClick(item.genre?.id ?: 0, !item.isChecked)
        }
        itemView.setOnClickListener {
            onClickListener.genreOnClick(item.genre?.id ?: 0, !item.isChecked)
        }
    }
}