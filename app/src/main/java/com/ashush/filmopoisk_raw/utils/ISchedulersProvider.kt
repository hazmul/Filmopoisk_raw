package com.ashush.filmopoisk_raw.utils

import io.reactivex.rxjava3.core.Scheduler

/**
 * Интерфес для провайдера [Scheduler]
 */

interface ISchedulersProvider {


    fun io(): Scheduler


    fun ui(): Scheduler
}