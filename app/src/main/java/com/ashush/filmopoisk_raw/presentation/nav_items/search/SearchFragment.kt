package com.ashush.filmopoisk_raw.presentation.nav_items.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.FragmentSearchBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.presentation.DetailActivity
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.nav_items.MoviesAdapter
import com.ashush.filmopoisk_raw.utils.DebouncingQueryTextListener

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )

        adapter.listener = object : MoviesAdapter.IListener {
            override fun onClick(movieId: Int) {
                startActivity(DetailActivity.newIntent(this@SearchFragment.requireActivity(), movieId))
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextSearchQuery.addTextChangedListener(DebouncingQueryTextListener(lifecycle) { query ->
            query?.let { viewModel.doRequest(it) }
        })

        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            result.movies?.let { adapter.update(it) }
        }
        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}