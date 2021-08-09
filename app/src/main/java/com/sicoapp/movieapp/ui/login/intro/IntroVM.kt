package com.sicoapp.movieapp.ui.login.intro

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.domain.Repository

class IntroVM  @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

}