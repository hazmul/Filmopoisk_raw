package com.ashush.filmopoisk_raw.di.utils

import com.ashush.filmopoisk_raw.utils.ISchedulersProvider
import com.ashush.filmopoisk_raw.SchedulersProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulersProviderModule {

    @Provides
    fun bindISchedulersProvider(): ISchedulersProvider = SchedulersProvider()

}