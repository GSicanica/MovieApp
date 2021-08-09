package com.sicoapp.movieapp.ui.crew

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.remote.response.movie.Cast
import com.sicoapp.movieapp.utils.URL_IMAGE
import kotlin.properties.Delegates


class CrewObservable(cast: Cast) : BaseObservable() {

    @get:Bindable
    var imageActorUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }

    @get:Bindable
    var actorName by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.actorName) }

    @get:Bindable
    var character by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.character) }

    init {
        imageActorUrl = URL_IMAGE + cast.profilePath
        actorName = cast.name
        character = cast.character
    }
}