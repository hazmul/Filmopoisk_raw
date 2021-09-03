package com.ashush.filmopoisk_raw.data.remote

import com.ashush.filmopoisk_raw.data.config.DataConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl {


    private var retrofit: Retrofit? = null

    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(DataConfig.baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    val retrofitService: APIRequests
        get() = getClient().create(APIRequests::class.java)
}