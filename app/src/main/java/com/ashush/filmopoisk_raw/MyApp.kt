package com.ashush.filmopoisk_raw

import android.app.Application
import android.content.Context
import com.ashush.filmopoisk_raw.di.AppComponent
import com.ashush.filmopoisk_raw.di.DaggerAppComponent
import dagger.BindsInstance
import dagger.Component

class MyApp: Application() {

    lateinit var application: AppComponent

    override fun onCreate() {
        super.onCreate()
        application = DaggerAppComponent.builder().setContext(this).build()
    }

}