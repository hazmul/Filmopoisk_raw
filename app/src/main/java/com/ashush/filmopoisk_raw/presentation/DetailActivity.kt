package com.ashush.filmopoisk_raw.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.MyApp
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.databinding.ActivityDetailBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.squareup.picasso.Picasso
import java.util.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID_KEY = "MOVIE_ID_KEY"

        @JvmStatic
        fun newIntent(context: Context, movieId: Int): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(MOVIE_ID_KEY, movieId)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailActivityViewModel
    private lateinit var binding: ActivityDetailBinding
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.application as MyApp).application.inject(this)
        viewModel = injectViewModel(this.viewModelFactory)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra(MOVIE_ID_KEY, 0)


        viewModel.requestResult.observe(this) { result ->
            updateUI(result)
        }
        viewModel.requestError.observe(this) { result ->
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
        viewModel.doRequest(movieId)
//
//        setSupportActionBar(binding.toolbarDetail)
//
//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)
    }



    private fun updateUI(movie: DataMovieDetailModel?) {
        Picasso.get()
            .load(DataConfig.getBasePosterUrl(DataConfig.config?.images?.posterSizes?.lastOrNull()) + movie?.posterPath)
            .into(binding.toolbarImg)
        binding.contentDetail.movieCountriesText.text = movie?.productionCountries?.map { it -> it?.name }?.reduce {str, item -> "$str, $item"}
        binding.contentDetail.movieGenresText.text = movie?.genres?.map { it -> it?.name }?.reduce {str, item -> "$str, $item"}?.lowercase(
            Locale.getDefault())
        if (movie != null) {
            binding.contentDetail.movieHomepageText.text = Html.fromHtml("<a href=\"${movie.homepage}\">${getString(R.string.official_site)}</a>")
            binding.contentDetail.movieHomepageText.isClickable = true
            binding.contentDetail.movieHomepageText.movementMethod = LinkMovementMethod.getInstance()
        }
        binding.contentDetail.movieOriginalLanguageText.text = movie?.originalLanguage
        binding.contentDetail.movieOverviewText.text = movie?.overview
        binding.contentDetail.movieReleaseDateText.text = movie?.releaseDate
        binding.contentDetail.movieTaglineText.text = movie?.tagline
        binding.contentDetail.movieProductionCompaniesText.text = movie?.productionCompanies?.map { it -> it?.name }?.reduce {str, item -> "$str, $item"}

    }
}