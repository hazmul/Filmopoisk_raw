package com.ashush.filmopoisk_raw.presentation.nav_items.categories.nowPlaying

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
import com.ashush.filmopoisk_raw.databinding.FragmentNowplayingBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.presentation.DetailFragment
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel
import com.ashush.filmopoisk_raw.presentation.nav_items.MoviesAdapter
import com.ashush.filmopoisk_raw.utils.RVLayoutManager

class NowPlayingFragment : Fragment() {

    private lateinit var viewModel: NowPlayingViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentNowplayingBinding? = null
    private val binding get() = _binding!!
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        _binding = FragmentNowplayingBinding.inflate(inflater, container, false)

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
        adapter.listener = object : MoviesAdapter.IListener {
            override fun onClick(movieId: Int) {
                val bundle = bundleOf(DetailFragment.MOVIE_ID_KEY to movieId)
                view.findNavController().navigate(R.id.action_nav_mainPager_to_detailFragment, bundle)
            }
        }
        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            result.movies?.let { adapter.update(it) }
        }
        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
        sharedViewModel.viewTypeLiveData.observe(viewLifecycleOwner) { result ->
            binding.recyclerView.layoutManager = RVLayoutManager.getLayout(requireActivity(), result)
        }

        viewModel.doRequest()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}