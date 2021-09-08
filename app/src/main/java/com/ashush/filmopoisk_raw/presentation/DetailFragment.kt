package com.ashush.filmopoisk_raw.presentation

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.databinding.FragmentDetailBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import java.util.*
import javax.inject.Inject

class DetailFragment : Fragment() {

    companion object {
        const val MOVIE_ID_KEY = "MOVIE_ID_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var isAllFabsVisible = false
    private var movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        movieId = arguments?.getInt(MOVIE_ID_KEY, 0)!!

        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)
        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            updateUI(result)
        }
        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
        viewModel.doRequest(movieId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleFab()
    }

    private fun handleFab() {
        val fabFf = requireActivity().findViewById<FloatingActionButton>(R.id.add_favorites_fab)
        val fabFw = requireActivity().findViewById<FloatingActionButton>(R.id.add_watchlist_fab)
        val fabRoot = requireActivity().findViewById<ExtendedFloatingActionButton>(R.id.add_fab)
        fabFf.visibility = View.GONE
        fabFw.visibility = View.GONE
        isAllFabsVisible = false
        fabRoot.shrink()
        fabRoot.setOnClickListener {
            if (!isAllFabsVisible) {
                fabFf.show()
                fabFw.show()
                fabRoot.extend()
                isAllFabsVisible = true
            } else {
                fabFf.hide()
                fabFw.hide()
                fabRoot.shrink()
                isAllFabsVisible = false
            }
        }
        fabFf.setOnClickListener {
            viewModel.requestResult.value?.let { viewModel.addToFavorite(it) }
        }
        fabFw.setOnClickListener {
            viewModel.requestResult.value?.let { viewModel.addToWatchlist(it) }
        }
    }

    override fun onDestroyView() {
        restoreMainToolbar()
        super.onDestroyView()
    }

    private fun updateUI(movie: DataMovieDetailModel?) {
        setDetailToolbar(movie)
        binding.movieCountriesText.text =
            movie?.productionCountries?.map { it -> it?.name }?.reduce { str, item -> "$str, $item" }
        binding.movieGenresText.text =
            movie?.genres?.map { it -> it?.name }?.reduce { str, item -> "$str, $item" }?.lowercase(
                Locale.getDefault()
            )
        if (movie != null) {
            binding.movieHomepageText.text =
                Html.fromHtml("<a href=\"${movie.homepage}\">${getString(R.string.official_site)}</a>")
            binding.movieHomepageText.isClickable = true
            binding.movieHomepageText.movementMethod = LinkMovementMethod.getInstance()
        }
        binding.movieOriginalLanguageText.text = movie?.originalLanguage
        binding.movieOverviewText.text = movie?.overview
        binding.movieReleaseDateText.text = movie?.releaseDate
        binding.movieTaglineText.text = movie?.tagline
        binding.movieProductionCompaniesText.text =
            movie?.productionCompanies?.map { it -> it?.name }?.reduce { str, item -> "$str, $item" }
    }

    private fun setDetailToolbar(movie: DataMovieDetailModel?) {
        requireActivity().findViewById<ExtendedFloatingActionButton>(R.id.add_fab)?.visibility = View.VISIBLE
        requireActivity().findViewById<ConstraintLayout>(R.id.add_fab_content)?.visibility = View.VISIBLE
        requireActivity().findViewById<ImageView>(R.id.toolbar_img_main)?.visibility = View.VISIBLE
        requireActivity().findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar).title =
            ("${movie?.originalTitle} (${"\\d{4}".toRegex().find(movie?.releaseDate!!)?.value})")

        Picasso.get()
            .load(DataConfig.getBasePosterUrl(DataConfig.config?.images?.posterSizes?.lastOrNull()) + movie.backdropPath)
            .into(requireActivity().findViewById<ImageView>(R.id.toolbar_img_main))
    }

    private fun restoreMainToolbar() {
        requireActivity().findViewById<ExtendedFloatingActionButton>(R.id.add_fab)?.visibility = View.GONE
        requireActivity().findViewById<ConstraintLayout>(R.id.add_fab_content)?.visibility = View.GONE
        requireActivity().findViewById<ImageView>(R.id.toolbar_img_main)?.visibility = View.GONE
        requireActivity().findViewById<ImageView>(R.id.toolbar_img_main)?.setImageDrawable(null)
        requireActivity().findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar).title = ""
    }
}