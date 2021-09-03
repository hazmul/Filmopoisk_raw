package com.ashush.filmopoisk_raw.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailActivityViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is DetailActivity"
    }
    val text: LiveData<String> = _text
}