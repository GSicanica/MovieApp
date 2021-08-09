package com.sicoapp.movieapp.ui.topmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.domain.Repository

class TopMovieViewModel @ViewModelInject constructor(
    private var repository: Repository
) : ViewModel() {
    fun getTopRated(pageId :Long) = repository.getTopRated(pageId)
}


