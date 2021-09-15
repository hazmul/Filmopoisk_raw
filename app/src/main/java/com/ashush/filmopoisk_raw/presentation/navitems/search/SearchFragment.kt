package com.ashush.filmopoisk_raw.presentation.navitems.search

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
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.FragmentSearchBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.presentation.DetailFragment
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.adapters.IListener
import com.ashush.filmopoisk_raw.presentation.navitems.adapters.MoviesAdapter
import com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter.showSearchFilterDialog
import com.ashush.filmopoisk_raw.utils.DebouncingQueryTextListener
import com.ashush.filmopoisk_raw.utils.RVLayoutManager

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var preBinding: FragmentSearchBinding? = null
    private val binding get() = preBinding!!
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        preBinding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            RVLayoutManager.getLayout(requireActivity(), sharedViewModel.viewTypeStatus.value)
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
            view.findNavController().navigate(R.id.actionNavSearchToDetailFragment, bundle)
        }

        binding.editTextSearchQuery.addTextChangedListener(DebouncingQueryTextListener(lifecycle) { query ->
            query?.let { viewModel.getOtherMovies(it) }
        })

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getMoviesAfterScrollDown()
                }
            }
        })

        binding.fragmentSearchFilters.setOnClickListener(::filtersAction)

        viewModel.filteredMovies.observe(viewLifecycleOwner) { movies ->
            movies?.let { adapter.update(it) }
        }
        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
        sharedViewModel.viewTypeStatus.observe(viewLifecycleOwner) { result ->
            binding.recyclerView.layoutManager = RVLayoutManager.getLayout(requireActivity(), result)
        }
    }

    private fun filtersAction(view: View?) {
        requireContext().showSearchFilterDialog(viewModel.filter) { filter ->
            viewModel.applyFilter(filter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        preBinding = null
    }
}