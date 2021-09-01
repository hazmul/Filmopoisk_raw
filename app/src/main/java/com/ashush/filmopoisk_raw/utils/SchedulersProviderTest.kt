package com.ashush.filmopoisk_raw

import com.ashush.filmopoisk_raw.utils.ISchedulersProvider
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Реализация интерфеса [ISchedulersProvider] для тестов
 */

class SchedulersProviderTest : ISchedulersProvider {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}