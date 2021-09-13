package com.ashush.filmopoisk_raw.presentation.nav_items

import android.annotation.SuppressLint
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
        fun onClick(movieId: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerItemBinding.bind(itemView)

        fun bindView(movie: DataMoviesModel.Movie, listener: IListener?) {
            binding.cardViewTextTitle.text = movie.title
            binding.cardViewTextOverView.text = when {
                movie.overview?.length!! >= 100 -> "${movie.overview.substring(0, 100)}..."
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movies[position]?.let { holder.bindView(it, listener) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(movies: List<DataMoviesModel.Movie?>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}