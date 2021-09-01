package com.ashush.filmopoisk_raw.di

import android.content.Context
import com.ashush.filmopoisk_raw.di.presentation.ViewModelModule
import com.ashush.filmopoisk_raw.di.utils.SchedulersProviderModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules =
    [
        SchedulersProviderModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

//    fun inject(mainActivity: MainActivity)
//    fun inject(detailActivity: DetailActivity)
}







