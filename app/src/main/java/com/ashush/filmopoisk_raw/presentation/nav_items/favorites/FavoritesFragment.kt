package com.ashush.filmopoisk_raw.presentation.nav_items.favorites

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
import com.ashush.filmopoisk_raw.data.db.DBRoom
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.databinding.FragmentFavoritesBinding
import com.ashush.filmopoisk_raw.databinding.FragmentNowplayingBinding
import com.ashush.filmopoisk_raw.models.data.movies.DataMovieDetailModel
import com.ashush.filmopoisk_raw.models.data.movies.DataMoviesModel
import com.ashush.filmopoisk_raw.presentation.DetailActivity
import com.ashush.filmopoisk_raw.presentation.nav_items.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var dbRoom: DBRoom

    private lateinit var favoritesViewModel: FavoritesViewModel
    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

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
                startActivity(DetailActivity.newIntent(this@FavoritesFragment.requireActivity(), movieId))
            }
        }

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        // Пробник запроса из бд
        val favoritesDao = dbRoom.favoritesDao()
        val resultDB = favoritesDao.getAll()
        var resultHTTP: MutableList<DataMoviesModel.Movie?> = mutableListOf()
        for (movie in resultDB) {
            val retrofitImpl = RetrofitImpl(requireContext()).retrofitService
            retrofitImpl.getMovieDetail(movie.movieId!!, DataConfig.API_KEY).enqueue(
                object : Callback<DataMovieDetailModel> {
                    override fun onFailure(call: Call<DataMovieDetailModel>, t: Throwable) {
                        Toast.makeText(requireActivity(), t.toString(), Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<DataMovieDetailModel>,
                        response: Response<DataMovieDetailModel>
                    ) {
                        resultHTTP.add(DataMoviesModel.Movie(title = response.body()?.title, posterPath = response.body()?.posterPath))
                        adapter.update(resultHTTP)
                    }

                }
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}