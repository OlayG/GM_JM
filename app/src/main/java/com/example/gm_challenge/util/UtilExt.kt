package com.example.gm_challenge.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.closeKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

val <T>Resource<T>?.isLoading : Boolean
    get() = this is Resource.Loading

val <T>Resource<T>?.isSuccess : Boolean
    get() = this is Resource.Success

val <T>Resource<T>?.isError : Boolean
    get() = this is Resource.Error