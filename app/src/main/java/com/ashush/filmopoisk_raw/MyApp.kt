package com.ashush.filmopoisk_raw

import android.app.Application
import android.content.Context
import com.ashush.filmopoisk_raw.di.AppComponent
import com.ashush.filmopoisk_raw.di.DaggerAppComponent
import dagger.BindsInstance
import dagger.Component

/**
 * Подкласс наследник класса [Application]. Отражает состояние данного приложения.
 *
 */

class MyApp: Application() {

    lateinit var application: AppComponent

    /**
     * При вызове onCreate() помещается контекст приложения в граф зависимостей
     */

    override fun onCreate() {
        super.onCreate()
        application = DaggerAppComponent.builder().setContext(this).build()
    }

}