package com.example.understandingmvvm.utils

data class Resource<out T>(val status: Status, val  data: T?, val message: String?, val code: Int) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, -1)
        }

        fun <T> error(msg: String, data: T?, code: Int): Resource<T> {
            return Resource(Status.ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, -1)
        }
    }
}