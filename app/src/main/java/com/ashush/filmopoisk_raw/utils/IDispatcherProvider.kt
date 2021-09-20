package com.ashush.filmopoisk_raw.utils

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}