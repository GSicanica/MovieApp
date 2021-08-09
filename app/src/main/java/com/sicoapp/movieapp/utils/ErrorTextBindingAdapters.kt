package com.sicoapp.movieapp.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object ErrorTextBindingAdapters {
    @JvmStatic
    @BindingAdapter("errorText")
    fun errorText(view: TextInputLayout, errorMessage: String) {
        view.error = errorMessage
    }
}