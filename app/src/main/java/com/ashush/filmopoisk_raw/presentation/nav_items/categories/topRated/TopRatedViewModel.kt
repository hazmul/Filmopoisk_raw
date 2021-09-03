package com.ashush.filmopoisk_raw.presentation.nav_items.categories.topRated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopRatedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is TopRated Fragment"
    }
    val text: LiveData<String> = _text
}