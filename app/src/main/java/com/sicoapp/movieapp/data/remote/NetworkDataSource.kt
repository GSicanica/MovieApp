package com.sicoapp.movieapp.data.remote

import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.Observable
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val movieService: MovieDao
) {

    fun getTopRated(pageId: Long): Observable<MovieResponse> {
        return movieService.getTopRated(API_KEY, pageId.toString())
    }

    fun getPopular(pageId: Long): Observable<MovieResponse> {
        return movieService.getPopular(API_KEY, pageId.toString())
    }

    fun getMulti(query: String): Observable<Multi> {
        return movieService.getMulti(API_KEY, query)
    }

    fun getMovieByID(movieId: Long): Observable<Movie> {
        return movieService.getMovieByID(movieId, API_KEY)
    }

    fun getTvShowById(movieId: Long): Observable<TvResponse> {
        return movieService.getTvShowById(movieId, API_KEY)
    }

    fun getCrewByMovieId(movieId: Long): Observable<Movie> {
        return movieService.getCrewByMovieId(movieId, API_KEY)
    }
}