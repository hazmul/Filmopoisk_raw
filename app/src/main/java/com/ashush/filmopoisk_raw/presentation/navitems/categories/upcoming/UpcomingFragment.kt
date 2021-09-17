package com.ashush.filmopoisk_raw.presentation.navitems.categories.upcoming

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
import com.ashush.filmopoisk_raw.databinding.FragmentUpcomingBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.presentation.DetailFragment
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.adapters.IListener
import com.ashush.filmopoisk_raw.presentation.navitems.adapters.MoviesAdapter
import com.ashush.filmopoisk_raw.utils.getLayout

/**
 * Фрагмент отображающий подборку фильмов в категории "скоро в прокате"
 */

class UpcomingFragment : Fragment() {

    private lateinit var viewModel: UpcomingViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var preBinding: FragmentUpcomingBinding? = null
    private val binding get() = preBinding!!
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        preBinding = FragmentUpcomingBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            getLayout(requireActivity(), sharedViewModel.viewTypeStatus.value)
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
            view.findNavController().navigate(R.id.actionNavMainPagerToDetailFragment, bundle)
        }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getMovies()
                }
            }
        })

        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }
        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            adapter.update(result)
        }
        sharedViewModel.viewTypeStatus.observe(viewLifecycleOwner) { result ->
            binding.recyclerView.layoutManager = getLayout(requireActivity(), result)
        }

        viewModel.getMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        preBinding = null
    }
}