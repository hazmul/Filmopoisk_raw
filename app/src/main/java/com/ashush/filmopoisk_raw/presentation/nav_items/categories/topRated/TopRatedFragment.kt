package com.ashush.filmopoisk_raw.presentation.nav_items.categories.topRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.databinding.FragmentTopratedBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.presentation.DetailActivity
import com.ashush.filmopoisk_raw.presentation.nav_items.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedFragment : Fragment() {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private var _binding: FragmentTopratedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        topRatedViewModel =
            ViewModelProvider(this).get(TopRatedViewModel::class.java)

        _binding = FragmentTopratedBinding.inflate(inflater, container, false)

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
                startActivity(DetailActivity.newIntent(this@TopRatedFragment.requireActivity(), movieId))
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // Пробник запроса
        val retrofitImpl = RetrofitImpl(requireContext()).retrofitService
        retrofitImpl.getMoviesTopRated(DataConfig.API_KEY).enqueue(
            object : Callback<DataMoviesModel> {
                override fun onFailure(call: Call<DataMoviesModel>, t: Throwable) {
                    Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<DataMoviesModel>,
                    response: Response<DataMoviesModel>
                ) {
                    response.body()?.movies?.let { adapter.update(it) }
                }

            }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}