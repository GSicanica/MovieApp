package com.sicoapp.movieapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.sicoapp.movieapp.ui.popular.PopularMovieFragment
import com.sicoapp.movieapp.ui.popular.PopularMovieAdapter
import com.sicoapp.movieapp.ui.search.SearchFragment
import com.sicoapp.movieapp.ui.search.SearchAdapter
import com.sicoapp.movieapp.ui.topmovie.TopMovieFragment
import com.sicoapp.movieapp.ui.topmovie.TopMovieAdapter
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val popularAdapter : PopularMovieAdapter,
    private val topMovieAdapter : TopMovieAdapter,
    private val searchAdapter : SearchAdapter
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            PopularMovieFragment::class.java.name -> PopularMovieFragment(popularAdapter)
            TopMovieFragment::class.java.name -> TopMovieFragment(topMovieAdapter)
            SearchFragment::class.java.name -> SearchFragment(searchAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}