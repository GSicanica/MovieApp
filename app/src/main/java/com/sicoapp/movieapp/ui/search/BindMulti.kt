package com.sicoapp.movieapp.ui.search

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.remote.response.multi.MultiResult
import com.sicoapp.movieapp.utils.URL_IMAGE
import kotlin.properties.Delegates

class BindMulti(val movie: MultiResult) : BaseObservable() {

    @get:Bindable
    var imageUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }

    init {
        imageUrl = URL_IMAGE + movie.poster_path
    }
}