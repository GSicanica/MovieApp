package com.sicoapp.movieapp.ui.login.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.domain.Repository

class SplashVM @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {
}