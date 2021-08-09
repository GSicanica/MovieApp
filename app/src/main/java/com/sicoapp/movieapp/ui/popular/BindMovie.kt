package com.sicoapp.movieapp.ui.popular

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.utils.URL_IMAGE
import kotlin.properties.Delegates

class BindMovie(val movie: Movie) : BaseObservable() {
    @get:Bindable
    var imageUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }
    init {
        imageUrl = URL_IMAGE + movie.posterPath
    }
}