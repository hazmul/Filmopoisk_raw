package com.ashush.filmopoisk_raw.presentation.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.FragmentLaunchBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.presentation.MainActivity
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel

class LaunchFragment : Fragment() {

    lateinit var viewModel: LaunchViewModel
    private val sharedViewModel: MainActivityViewModel by activityViewModels()
    private var preBinding: FragmentLaunchBinding? = null
    private val binding get() = preBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = injectViewModel((requireActivity() as MainActivity).viewModelFactory)

        requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
            .setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        preBinding = FragmentLaunchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animateLoading()

        viewModel.requestResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                view.findNavController().navigate(R.id.actionLaunchFragmentToNavMainPager)
            }
        }
        viewModel.requestError.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireActivity(), result, Toast.LENGTH_SHORT).show()
        }

        sharedViewModel.loadAppSettings()
    }

    private fun animateLoading() {
        val animation = RotateAnimation(
            360F,
            0F,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        animation.repeatMode = Animation.RESTART
        animation.repeatCount = Animation.INFINITE
        animation.interpolator = LinearInterpolator()
        animation.duration = 1000L
        binding.launchIcon.startAnimation(animation)
    }

    override fun onDestroyView() {
        requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
            .setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        super.onDestroyView()
    }
}