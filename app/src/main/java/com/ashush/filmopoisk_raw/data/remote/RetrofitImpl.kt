package com.ashush.filmopoisk_raw.data.remote

import android.content.Context
import com.ashush.filmopoisk_raw.data.config.DataConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitImpl @Inject constructor(context: Context) {

    private var retrofit: Retrofit? = null
    private var okHttpClientCustomizer = OkHttpClientCustomizer(context)

    private fun getClient(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(okHttpClientCustomizer.loggingInterceptor)
            .addInterceptor(okHttpClientCustomizer.offlineInterceptor)
            .addNetworkInterceptor(okHttpClientCustomizer.onlineInterceptor)
            .cache(okHttpClientCustomizer.cache)
            .build()


        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(DataConfig.baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit!!
    }

    val retrofitService: APIRequests
        get() = getClient().create(APIRequests::class.java)
}