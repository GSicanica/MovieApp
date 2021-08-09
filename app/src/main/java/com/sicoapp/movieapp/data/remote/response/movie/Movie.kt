package com.sicoapp.movieapp.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val genres: List<Genre>,
    val credits: Credits?,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val popularity: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String?
)
