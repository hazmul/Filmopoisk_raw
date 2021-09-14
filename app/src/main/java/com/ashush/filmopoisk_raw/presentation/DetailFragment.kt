package com.ashush.filmopoisk_raw.presentation

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.FragmentDetailBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.domain.models.DetailedMovie
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment : Fragment() {

    companion object {
        const val MOVIE_ID_KEY = "MOVIE_ID_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var preBinding: FragmentDetailBinding? = null
    private val binding get() = preBinding!!
    private var isAllFabsVisible = false
    private var movieId: Int = 0

    private var favoriteFAB: FloatingActionButton? = null
    private var watchlistFAB: FloatingActionButton? = null
    private var expandedFAB: ExtendedFloatingActionButton? = null
    private var rootFAB: ConstraintLayout? = null
    private var toolBarImg: ImageView? = null
    private var collapsingToolbar: CollapsingToolbarLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        preBinding = FragmentDetailBinding.inflate(inflater, container, false)
        movieId = arguments?.getInt(MOVIE_ID_KEY, 0)!!

        activity?.let {
            favoriteFAB = it.findViewById(R.id.add_favorites_fab)
            watchlistFAB = it.findViewById(R.id.add_watchlist_fab)
            expandedFAB = it.findViewById(R.id.add_fab)
            rootFAB = it.findViewById(R.id.add_fab_content)
            toolBarImg = it.findViewById(R.id.toolbar_img_main)
            collapsingToolbar = it.findViewById(R.id.collapsing_toolbar)
        }

        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)
        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            updateUI(result)
        }
        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
        viewModel.inFavorite.observe(viewLifecycleOwner) { result ->
            handleFavoriteStatusInFab(result)
        }
        viewModel.inWatchlist.observe(viewLifecycleOwner) { result ->
            handleWatchlistStatusInFab(result)
        }

        viewModel.getMovieInfo(movieId)
        sharedViewModel.optionMenuIsNeeded.value = false

        handleFab()
        return binding.root
    }

    override fun onDestroyView() {
        sharedViewModel.optionMenuIsNeeded.value = true
        restoreMainToolbar()
        super.onDestroyView()
    }

    private fun handleFab() {
        favoriteFAB?.visibility = View.GONE
        watchlistFAB?.visibility = View.GONE
        isAllFabsVisible = false
        expandedFAB?.shrink()
        expandedFAB?.setOnClickListener {
            if (!isAllFabsVisible) {
                favoriteFAB?.show()
                watchlistFAB?.show()
                expandedFAB?.extend()
                isAllFabsVisible = true
            } else {
                favoriteFAB?.hide()
                watchlistFAB?.hide()
                expandedFAB?.shrink()
                isAllFabsVisible = false
            }
        }
        favoriteFAB?.setOnClickListener {
            viewModel.requestResult.value?.let { viewModel.toFavoriteClicked(it) }
        }
        watchlistFAB?.setOnClickListener {
            viewModel.requestResult.value?.let { viewModel.toWatchlistClicked(it) }
        }
    }

    private fun handleFavoriteStatusInFab(result: Boolean?) {
        if (result == true) {
            favoriteFAB?.drawable?.setTint(getColor(resources, R.color.in_favorite, activity?.theme))
        } else {
            favoriteFAB?.drawable?.setTint(getColor(resources, R.color.not_favorite, activity?.theme))
        }
    }

    private fun handleWatchlistStatusInFab(result: Boolean?) {
        if (result == true) {
            watchlistFAB?.drawable?.setTint(getColor(resources, R.color.in_watchlist, activity?.theme))
        } else {
            watchlistFAB?.drawable?.setTint(getColor(resources, R.color.not_watchlist, activity?.theme))
        }
    }

    private fun updateUI(movie: DetailedMovie) {
        setDetailToolbar(movie)
        binding.apply {
            movieCountriesText.text = movie.productionCountries
            movieGenresText.text = movie.genres
            movieHomepageText.text = HtmlCompat.fromHtml(
                "<a href=\"${movie.homepage}\">${getString(R.string.official_site)}</a>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            movieHomepageText.isClickable = true
            movieHomepageText.movementMethod = LinkMovementMethod.getInstance()
            movieOriginalLanguageText.text = movie.originalLanguage
            movieOverviewText.text = movie.overview
            movieReleaseDateText.text = movie.releaseDate
            movieTaglineText.text = movie.tagline
            movieProductionCompaniesText.text = movie.productionCompanies
        }
    }

    private fun setDetailToolbar(movie: DetailedMovie) {
        expandedFAB?.visibility = View.VISIBLE
        rootFAB?.visibility = View.VISIBLE
        toolBarImg?.visibility = View.VISIBLE
        collapsingToolbar?.title = ("${movie.title} (${"\\d{4}".toRegex().find(movie.releaseDate)?.value})")

        Picasso.get()
            .load(movie.backdropPath)
            .into(requireActivity().findViewById<ImageView>(R.id.toolbar_img_main))
    }

    private fun restoreMainToolbar() {
        expandedFAB?.visibility = View.GONE
        rootFAB?.visibility = View.GONE
        toolBarImg?.visibility = View.GONE
        toolBarImg?.setImageDrawable(null)
        collapsingToolbar?.title = ""
    }
}