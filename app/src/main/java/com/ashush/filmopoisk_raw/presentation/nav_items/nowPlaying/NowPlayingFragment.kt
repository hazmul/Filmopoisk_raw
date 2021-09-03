package com.ashush.filmopoisk_raw.presentation.nav_items.nowPlaying

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
import com.ashush.filmopoisk_raw.databinding.FragmentNowplayingBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.presentation.nav_items.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingFragment : Fragment() {



    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private var _binding: FragmentNowplayingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nowPlayingViewModel =
            ViewModelProvider(this).get(NowPlayingViewModel::class.java)

        _binding = FragmentNowplayingBinding.inflate(inflater, container, false)

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
        retrofitImpl.getMoviesNowPlaying(DataConfig.API_KEY).enqueue(
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