package com.ashush.filmopoisk_raw.presentation.navitems.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel
import javax.inject.Inject

/**
 * Экран отображающий настройки приложения.
 * Заключает в себе логику обработки их переключений.
 */

class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val sharedViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        sharedViewModel.optionMenuIsNeeded.value = false

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs_settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            getString(R.string.feedback_key) -> {
                Toast.makeText(requireActivity(), getString(R.string.feedback_summary), Toast.LENGTH_SHORT).show()
                return true
            }
            getString(R.string.theme_style_key) -> {
                sharedViewModel.loadAppSettings()
                return true
            }
        }
        return super.onPreferenceTreeClick(preference)
    }

    override fun onDestroyView() {
        sharedViewModel.optionMenuIsNeeded.value = true
        super.onDestroyView()
    }
}