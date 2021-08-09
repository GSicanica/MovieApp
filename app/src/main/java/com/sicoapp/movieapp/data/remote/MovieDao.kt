package com.sicoapp.movieapp.data.remote

import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.movie.MovieResponse
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDao {

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Observable<MovieResponse>

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Observable<MovieResponse>

    @GET("search/multi?")
    fun getMulti(
        @Query(value = "api_key") apiKey: String,
        @Query(value = "query") searchTitle: String
    ) : Observable<Multi>

    @GET("movie/{id}?&append_to_response=credits")
    fun getCrewByMovieId(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Observable<Movie>

    @GET("tv/{id}")
    fun getTvShowById(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Observable<TvResponse>

    @GET("movie/{id}")
    fun getMovieByID(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): Observable<Movie>

}