package com.sicoapp.movieapp.ui.profil

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlin.properties.Delegates

class BindMyProfile() : BaseObservable() {
    @get:Bindable
    var image :String? by Delegates.observable("TEST image") { _, _, _ -> notifyPropertyChanged(BR.image) }

    @get:Bindable
    var name :String? by Delegates.observable("TEST name") { _, _, _ -> notifyPropertyChanged(BR.name) }

    @get:Bindable
    var email :String? by Delegates.observable("TEST email") { _, _, _ -> notifyPropertyChanged(BR.email) }
}