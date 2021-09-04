package com.ashush.filmopoisk_raw.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.databinding.ActivityDetailBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

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

    private lateinit var detailActivityViewModel: DetailActivityViewModel
    private lateinit var binding: ActivityDetailBinding
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra(MOVIE_ID_KEY, 0)
//
//        setSupportActionBar(binding.toolbarDetail)
//
//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)

        detailActivityViewModel = ViewModelProvider(this).get(DetailActivityViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        // Пробник запроса
        val retrofitImpl = RetrofitImpl(applicationContext).retrofitService
        retrofitImpl.getMovieDetail(movieId, DataConfig.API_KEY).enqueue(
            object : Callback<DataMovieDetailModel> {
                override fun onFailure(call: Call<DataMovieDetailModel>, t: Throwable) {
                    Log.d("TAG", "onFailure() called with: call = $call, t = $t")
                    Toast.makeText(this@DetailActivity, t.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<DataMovieDetailModel>,
                    response: Response<DataMovieDetailModel>
                ) {
                    val movie = response.body()
                    Log.d("TAG", "onResponse() called with: call = $call, movie = $movie")
                    Picasso.get()
                        .load(DataConfig.getBasePosterUrl(DataConfig.config?.images?.posterSizes?.lastOrNull()) + movie?.posterPath)
                        .into(binding.toolbarImg)
                    binding.contentDetail.movieCountriesText.text = movie?.productionCountries?.map { it -> it?.name }?.reduce {str, item -> "$str, $item"}
                    binding.contentDetail.movieGenresText.text = movie?.genres?.map { it -> it?.name }?.reduce {str, item -> "$str, $item"}?.lowercase(Locale.getDefault())
                    if (movie != null) {
                        binding.contentDetail.movieHomepageText.text = Html.fromHtml("<a href=\"${movie.homepage}\">${getString(R.string.official_site)}</a>")
                        binding.contentDetail.movieHomepageText.isClickable = true
                        binding.contentDetail.movieHomepageText.movementMethod = LinkMovementMethod.getInstance()
                    }
                    binding.contentDetail.movieOriginalLanguageText.text = movie?.originalLanguage
                    binding.contentDetail.movieOverviewText.text = movie?.overview
                    binding.contentDetail.movieReleaseDateText.text = movie?.releaseDate
                    binding.contentDetail.movieTaglineText.text = movie?.tagline
                    binding.contentDetail.movieProductionCompaniesText.text = movie?.productionCountries?.map { it -> it?.name }?.reduce {str, item -> "$str, $item"}














                }

            }
        )
    }
}