package com.ashush.filmopoisk_raw.presentation.navitems.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.databinding.FragmentMainPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Фрагмент для отображения "страничек" с подборками
 */

class MainPagerFragment : Fragment() {

    private var preBinding: FragmentMainPagerBinding? = null

    private val binding get() = preBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        preBinding = FragmentMainPagerBinding.inflate(inflater, container, false)

        binding.pager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = (binding.pager.adapter as ViewPagerAdapter).fragmentNames[position]
        }.attach()

        return binding.root
    }

}
