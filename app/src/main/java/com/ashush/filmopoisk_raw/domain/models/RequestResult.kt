package com.ashush.filmopoisk_raw.domain.models

sealed class RequestResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): RequestResult<T>(data)
    class Error<T>(data: T?, message: String): RequestResult<T>(data, message)
}