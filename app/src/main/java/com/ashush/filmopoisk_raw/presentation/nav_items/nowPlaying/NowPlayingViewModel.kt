package com.ashush.filmopoisk_raw.presentation.nav_items.nowPlaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NowPlayingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is NowPlaying Fragment"
    }
    val text: LiveData<String> = _text
}