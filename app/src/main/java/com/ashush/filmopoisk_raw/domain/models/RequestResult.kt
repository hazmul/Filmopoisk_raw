package com.ashush.filmopoisk_raw.domain.models

/**
 * Класс объектов для ответа бизнес слою на запросы
 * @param T тип данных содержащий в поле [data]
 * @property data данные в ответе
 * @property message сообщение об ошибке в ответе
 * - [Success] объект для успешного запроса
 * - [Error] объект для не успешного запроса
 */

sealed class RequestResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): RequestResult<T>(data)
    class Error<T>(data: T?, message: String): RequestResult<T>(data, message)
}