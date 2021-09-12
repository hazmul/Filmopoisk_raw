package com.ashush.filmopoisk_raw.presentation.nav_items.search

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
import com.ashush.filmopoisk_raw.databinding.FragmentSearchBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.presentation.DetailFragment
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel
import com.ashush.filmopoisk_raw.presentation.nav_items.PagedMoviesAdapter
import com.ashush.filmopoisk_raw.presentation.nav_items.search.dialogsearchfilter.showSearchFilterDialog
import com.ashush.filmopoisk_raw.utils.DebouncingQueryTextListener
import com.ashush.filmopoisk_raw.utils.RVLayoutManager

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter = PagedMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)

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

        adapter.listener = object : PagedMoviesAdapter.IListener {
            override fun onClick(movieId: Int) {
                val bundle = bundleOf(DetailFragment.MOVIE_ID_KEY to movieId)
                view.findNavController().navigate(R.id.action_nav_search_to_detailFragment, bundle)
            }
        }

        binding.editTextSearchQuery.addTextChangedListener(DebouncingQueryTextListener(lifecycle) { query ->
            query?.let {
                viewModel.getMovies(it).observe(viewLifecycleOwner) { pagingData ->
                    adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                }
            }
        })

        binding.fragmentSearchFilters.setOnClickListener(::filtersAction)

        viewModel.filter.observe(viewLifecycleOwner) { result ->
            viewModel.lastQuery.value?.let {
                viewModel.getMovies(it).observe(viewLifecycleOwner) { pagingData ->
                    adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
                }
            }
            adapter.notifyDataSetChanged()
        }

        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
        sharedViewModel.viewTypeLiveData.observe(viewLifecycleOwner) { result ->
            binding.recyclerView.layoutManager = RVLayoutManager.getLayout(requireActivity(), result)
        }
    }

    private fun filtersAction(view: View?) {
        requireContext().showSearchFilterDialog(viewModel.filter.value!!) { filter ->
            viewModel.applyFilter(filter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}