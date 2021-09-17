package com.ashush.filmopoisk_raw.data.remote

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Класс провайдер [RetrofitService]
 * @property retrofit настроеная сущность [Retrofit]
 */
class RetrofitServiceProvider @Inject constructor(private val retrofit: Retrofit) {
    /**
     * Функция
     * @return возвращает реализацию [RetrofitService] для заданного [retrofit]
     */
    fun getService(): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}