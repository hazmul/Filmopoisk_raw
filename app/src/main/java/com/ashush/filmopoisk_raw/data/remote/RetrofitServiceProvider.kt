package com.ashush.filmopoisk_raw.data.remote

import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitServiceProvider @Inject constructor(private val retrofit: Retrofit) {

    fun getService(): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}