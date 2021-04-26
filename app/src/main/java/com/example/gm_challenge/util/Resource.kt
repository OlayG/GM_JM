package com.example.gm_challenge.util

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val msg: String) : Resource<T>()
}