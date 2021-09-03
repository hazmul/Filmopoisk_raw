package com.ashush.filmopoisk_raw.presentation.nav_items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.databinding.RecyclerItemBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.squareup.picasso.Picasso

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies: List<DataMoviesModel.Movie?> = emptyList()

    lateinit var listener: IListener

    interface IListener {
        fun onClick()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerItemBinding.bind(itemView)

        fun bindView(movie: DataMoviesModel.Movie) {
            binding.cardViewTextView.text = movie.title
            Picasso.get().load(DataConfig.posterBaseURL + movie.posterPath)
                .into(binding.cardViewIconImageView)
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

    fun update(movies: List<DataMoviesModel.Movie?>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}