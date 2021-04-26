package com.example.gm_challenge.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

const val SERVER_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss'Z'"

enum class FormatType(val pattern: String) {
    YYYY("yyyy")
}

@BindingAdapter("setAdapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("formatDate")
fun MaterialTextView.formatDate(date: String) {
    val formattedDate = try {
        val sdf = SimpleDateFormat(SERVER_TIMESTAMP, Locale.getDefault())
        sdf.parse(date)?.let {
            SimpleDateFormat(FormatType.YYYY.pattern, Locale.getDefault()).format(it)
        }
    } catch (e: Exception) {
        null
    }
    text = formattedDate
}

@BindingAdapter("isVisible")
fun View.isVisible(visible: Boolean) {
    visibility = when (visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

@BindingAdapter("setErrorMessage")
fun <T> TextInputLayout.setErrorMessage(data: Resource<T>?) {
    if (data is Resource.Error) error = data.msg
    isErrorEnabled = data.isError
}
