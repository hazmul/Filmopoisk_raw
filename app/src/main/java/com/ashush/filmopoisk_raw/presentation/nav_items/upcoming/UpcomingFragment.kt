package com.ashush.filmopoisk_raw.presentation.nav_items.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashush.filmopoisk_raw.data.config.DataConfig.Companion.API_KEY
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.databinding.FragmentUpcomingBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesResponse
import com.ashush.filmopoisk_raw.presentation.nav_items.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingFragment : Fragment() {

    private lateinit var upcomingViewModel: UpcomingViewModel
    private var _binding: FragmentUpcomingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        upcomingViewModel =
            ViewModelProvider(this).get(UpcomingViewModel::class.java)

        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }




    override fun onStart() {
        super.onStart()

        val adapter = MoviesAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )
        // Пробник запроса
        val retrofitImpl = RetrofitImpl().retrofitService
        retrofitImpl.getMoviesUpcoming(API_KEY).enqueue(
            object : Callback<DataMoviesResponse> {
                override fun onFailure(call: Call<DataMoviesResponse>, t: Throwable) {
                    Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<DataMoviesResponse>,
                    response: Response<DataMoviesResponse>
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