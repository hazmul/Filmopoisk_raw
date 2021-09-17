package com.ashush.filmopoisk_raw.di.presentation

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Класс-аннотация декларирующая ключ для собирания объектов в карту [map]
 * Используется для собирания [ViewModel]
 */

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)