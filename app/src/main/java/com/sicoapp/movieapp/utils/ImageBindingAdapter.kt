package com.sicoapp.movieapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sicoapp.movieapp.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 40f
        start()
    }
}

fun ImageView.loadImage(uri: String, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .centerCrop()
        .into(this)
}


object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String) {
        view.loadImage(url, getProgressDrawable(view.context))
    }
}

object ImageBindingAdapterDetails {
    @JvmStatic
    @BindingAdapter("loadImageDetails")
    fun loadImageDetails(view: ImageView, profileImage: String?) {
        Glide.with(view.context)
            .load(profileImage)
            .fitCenter()
            .into(view)
    }
}











