package com.ashush.filmopoisk_raw.di

import android.content.Context
import com.ashush.filmopoisk_raw.di.data.DBModule
import com.ashush.filmopoisk_raw.di.data.StoreModule
import com.ashush.filmopoisk_raw.di.presentation.ViewModelModule
import com.ashush.filmopoisk_raw.di.utils.SchedulersProviderModule
import com.ashush.filmopoisk_raw.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules =
    [
        SchedulersProviderModule::class,
        ViewModelModule::class,
        StoreModule::class,
        DBModule::class,
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}







