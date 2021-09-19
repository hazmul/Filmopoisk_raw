package com.ashush.filmopoisk_raw.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.FragmentDetailBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.domain.models.DomainDetailedMovie
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Экран приложения показывающий детальную информцию о фильме.
 * Содержит в себе логику работы FAB кнопок добавляющие/убирающие фильм в категорию "watchlist" и "favorite".
 *
 */

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        getData()
        initUI(inflater, container)
        handleFab()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()
    }

    private fun getData() {
        movieId = arguments?.getInt(MOVIE_ID_KEY, 0)!!
    }

    private fun initUI(inflater: LayoutInflater, container: ViewGroup?) {
        preBinding = FragmentDetailBinding.inflate(inflater, container, false)
        activity?.let {
            favoriteFAB = it.findViewById(R.id.add_favorites_fab)
            watchlistFAB = it.findViewById(R.id.add_watchlist_fab)
            expandedFAB = it.findViewById(R.id.add_fab)
            rootFAB = it.findViewById(R.id.add_fab_content)
        }
        binding.home.setOnClickListener( View.OnClickListener { activity?.onBackPressed() })
        viewModel.getMovieInfo(movieId)
        sharedViewModel.optionMenuIsNeeded.value = false
    }

    private fun bindObservers() {
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

    private fun updateUI(movieDomain: DomainDetailedMovie) {
        setDetailToolbar(movieDomain)
        binding.detailContent.apply {
            movieCountriesText.text = movieDomain.productionCountries
            movieGenresText.text = movieDomain.genres
            movieHomepageText.text = HtmlCompat.fromHtml(
                "<a href=\"${movieDomain.homepage}\">${getString(R.string.official_site)}</a>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            movieHomepageText.isClickable = true
            movieHomepageText.movementMethod = LinkMovementMethod.getInstance()
            movieOriginalLanguageText.text = movieDomain.originalLanguage
            movieOverviewText.text = movieDomain.overview
            movieReleaseDateText.text = movieDomain.releaseDate
            movieTaglineText.text = movieDomain.tagline
            movieProductionCompaniesText.text = movieDomain.productionCompanies
        }
    }

    private fun setDetailToolbar(movieDomain: DomainDetailedMovie) {
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        expandedFAB?.visibility = View.VISIBLE
        rootFAB?.visibility = View.VISIBLE
        binding.title.text = movieDomain.title

        Picasso.get()
            .load(movieDomain.backdropPath)
            .into(binding.toolbarImage)
    }

    private fun restoreMainToolbar() {
        expandedFAB?.visibility = View.GONE
        rootFAB?.visibility = View.GONE
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }
}