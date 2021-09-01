package com.ashush.filmopoisk_raw.presentation.nav_items.topRated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.databinding.FragmentTopratedBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedFragment : Fragment() {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private var _binding: FragmentTopratedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        topRatedViewModel =
            ViewModelProvider(this).get(TopRatedViewModel::class.java)

        _binding = FragmentTopratedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textToprated
        topRatedViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onStart() {
        super.onStart()
        // Пробник запроса
        val retrofitImpl = RetrofitImpl().retrofitService
        retrofitImpl.getMoviesTopRated(DataConfig.API_KEY).enqueue(
            object : Callback<DataMoviesResponse> {
                override fun onFailure(call: Call<DataMoviesResponse>, t: Throwable) {
                    binding.textToprated.text = t.toString()
                }

                override fun onResponse(
                    call: Call<DataMoviesResponse>,
                    response: Response<DataMoviesResponse>
                ) {
                    binding.textToprated.text = response.body().toString()
                }

            }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}