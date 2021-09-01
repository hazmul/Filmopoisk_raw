package com.ashush.filmopoisk_raw.presentation.nav_items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesResponse

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies: List<DataMoviesResponse.Movies?> = emptyList()

    lateinit var listener: IListener

    interface IListener {
        fun onClick()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerItemBinding.bind(itemView)

        fun bindView(movies: DataMoviesResponse.Movies) {
            binding.cardViewTextView.text = movies.title
            binding.cardViewIconImageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    itemView.resources,
                    R.drawable.ic_launcher_foreground,
                    null
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movies[position]?.let { holder.bindView(it) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun update(movies: List<DataMoviesResponse.Movies?>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}