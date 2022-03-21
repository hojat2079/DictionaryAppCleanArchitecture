package com.application.dictionaryclean.core.util


typealias simpleResource = Resource<Any>

sealed class Resource<T>(val message: String? = null, val data: T? = null) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>(data: T?=null) : Resource<T>(data = data)
    class Error<T>(data: T?, message: String?) : Resource<T>(message = message, data = data)
}