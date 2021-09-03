package com.ashush.filmopoisk_raw.presentation.nav_items.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WatchlistViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Watchlist Fragment"
    }
    val text: LiveData<String> = _text
}