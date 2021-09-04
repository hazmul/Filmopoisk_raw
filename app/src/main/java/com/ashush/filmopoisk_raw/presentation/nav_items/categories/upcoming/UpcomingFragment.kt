package com.ashush.filmopoisk_raw.presentation.nav_items.categories.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.config.DataConfig.Companion.API_KEY
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.databinding.FragmentUpcomingBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.nav_items.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingFragment : Fragment() {

    private lateinit var viewModel: UpcomingViewModel
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
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

//                startActivity(DetailActivity.newIntent(this@UpcomingFragment.requireActivity(), movieId))
                view.findNavController().navigate(R.id.action_nav_mainPager_to_blankFragment)

            }
        }
        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            result.movies?.let { adapter.update(it) }
        }
        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }

        viewModel.doRequest()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}