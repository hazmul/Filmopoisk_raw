package com.ashush.filmopoisk_raw

import com.ashush.filmopoisk_raw.utils.ISchedulersProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Реализация интерфеса [ISchedulersProvider]
 */

class SchedulersProvider : ISchedulersProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}