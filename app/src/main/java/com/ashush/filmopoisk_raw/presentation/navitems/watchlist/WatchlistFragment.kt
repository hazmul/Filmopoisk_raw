package com.ashush.filmopoisk_raw.presentation.navitems.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.FragmentWatchlistBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.presentation.DetailFragment
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.adapters.DetailedMoviesAdapter
import com.ashush.filmopoisk_raw.presentation.navitems.adapters.IListener
import com.ashush.filmopoisk_raw.utils.RVLayoutManager

class WatchlistFragment : Fragment() {

    private lateinit var viewModel: WatchlistViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var preBinding: FragmentWatchlistBinding? = null
    private val binding get() = preBinding!!
    private val adapter = DetailedMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        preBinding = FragmentWatchlistBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            RVLayoutManager.getLayout(requireActivity(), sharedViewModel.viewTypeLiveData.value)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.listener = IListener { movieId ->
            val bundle = bundleOf(DetailFragment.MOVIE_ID_KEY to movieId)
            view.findNavController().navigate(R.id.actionNavWatchlistToDetailFragment, bundle)
        }

        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            adapter.update(result)
        }
        sharedViewModel.viewTypeLiveData.observe(viewLifecycleOwner) { result ->
            binding.recyclerView.layoutManager = RVLayoutManager.getLayout(requireActivity(), result)
        }

        viewModel.getMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        preBinding = null
    }
}