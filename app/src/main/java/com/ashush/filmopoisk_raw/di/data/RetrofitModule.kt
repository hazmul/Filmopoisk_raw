package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {
    @Provides
    fun provideRetrofit(applicationContext: Context): RetrofitImpl {
        return RetrofitImpl(applicationContext)
    }
}