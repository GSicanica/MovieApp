package com.sicoapp.movieapp.data.remote.response.movie

data class MovieResponse (
    val page: Int,
    val results: List<Movie>
)