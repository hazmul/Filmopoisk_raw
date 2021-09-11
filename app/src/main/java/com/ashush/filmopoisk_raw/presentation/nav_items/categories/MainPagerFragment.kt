package com.ashush.filmopoisk_raw.presentation.nav_items.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.databinding.FragmentMainPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainPagerFragment : Fragment() {

    private lateinit var mainPagerViewModel: MainPagerViewModel
    private var _binding: FragmentMainPagerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainPagerViewModel =
            ViewModelProvider(this).get(MainPagerViewModel::class.java)

        _binding = FragmentMainPagerBinding.inflate(inflater, container, false)

        binding.pager.adapter =
            ViewPagerAdapter(requireActivity()) //Attach the adapter with our ViewPagerAdapter passing the host activity
        //Sets tabs names as mentioned in ViewPagerAdapter fragmentNames array, this can be implemented in many different ways.
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = (binding.pager.adapter as ViewPagerAdapter).fragmentNames[position]
        }.attach()

        return binding.root
    }

}
