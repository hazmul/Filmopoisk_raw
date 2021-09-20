package com.ashush.filmopoisk_raw.di.utils

import com.ashush.filmopoisk_raw.utils.DispatcherProvider
import com.ashush.filmopoisk_raw.utils.IDispatcherProvider
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {
    @Provides
    fun provideDispatcherProvider(): IDispatcherProvider {
        return DispatcherProvider()
    }
}