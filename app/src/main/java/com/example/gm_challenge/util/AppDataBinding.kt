package com.example.gm_challenge.util

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setAdapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("formatDate")
fun MaterialTextView.formatDate(date: String) {
    val formattedDate = try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.parse(date)?.let {
            SimpleDateFormat("yyyy", Locale.getDefault()).format(it)
        }
    } catch (e: Exception) {
        null
    }
    text = formattedDate
}

@BindingAdapter("formatPrice")
fun MaterialTextView.formatPrice(price: Double) {
    text = price.toString()
}

@BindingAdapter("onButtonClicked")
fun MaterialButton.onButtonClicked(f: Function0<Unit>) = setOnClickListener {
    f.invoke()
    closeKeyboard()
}

@BindingAdapter("onEditorEnterAction")
fun TextInputEditText.onEditorEnterAction(f: Function0<Unit>) {
    setOnEditorActionListener { _, actionId, event ->

        val keyDownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER
                && event.action == KeyEvent.ACTION_DOWN

        return@setOnEditorActionListener if(actionId == EditorInfo.IME_ACTION_GO || keyDownEvent) {
            f.invoke();closeKeyboard()
            true
        } else {
            false
        }
    }
}

@BindingAdapter("isVisible")
fun View.isVisible(visible: Boolean) {
    visibility = when(visible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

@BindingAdapter("setErrorMessage")
fun <T> TextInputLayout.setErrorMessage(data: Resource<T>?) {
    if(data is Resource.Error) error = data.msg
    isErrorEnabled = data.isError
}

@BindingAdapter("resetErrorEnabled")
fun TextInputLayout.resetErrorEnabled(reset : Boolean) {
    if(reset) isErrorEnabled = false
}
