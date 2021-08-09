package com.sicoapp.movieapp.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.domain.Repository

class SearchViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    fun searchForImage(query: String) = repository.getMulti(query)

}
