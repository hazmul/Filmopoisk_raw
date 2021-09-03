package com.ashush.filmopoisk_raw.presentation.nav_items.categories.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpcomingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Upcoming Fragment"
    }
    val text: LiveData<String> = _text

}