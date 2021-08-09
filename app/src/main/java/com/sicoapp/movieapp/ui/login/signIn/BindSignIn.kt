package com.sicoapp.movieapp.ui.login.signIn

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlin.properties.Delegates

class BindSignIn() : BaseObservable() {

    @get:Bindable
    var emailSignIn :String? by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.emailSignIn) }

    @get:Bindable
    var passwordSignIn :String? by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.passwordSignIn) }

}