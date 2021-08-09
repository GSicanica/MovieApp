package com.sicoapp.movieapp.ui.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.Rating
import com.sicoapp.movieapp.data.database.UserRatingsCrossRef
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.data.remote.response.tvShow.TvResponse
import com.sicoapp.movieapp.domain.Repository
import com.sicoapp.movieapp.utils.URL_IMAGE
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel @ViewModelInject constructor(
    private var repository: Repository,
) : ViewModel() {

    var bindDetails = BindDetails()

    fun insertSmiley(itemID: Int, rating: Int) {
        repository.insertSmiley(itemID, rating)
    }

    suspend fun insertUserMovieRatingCrossRef(crossRef: UserRatingsCrossRef) {
        repository.insertUserMovieRatingCrossRef(crossRef)
    }

    fun getSmileyByMovieId(
        itemId: Int
    ): Observable<Rating> {
        return repository.getSmileyByMovieId(itemId)
    }

    fun deleteSmileyByMovieId(itemId: Int) {
        repository.deleteSmileyByMovieId(itemId)
    }

    fun getMovieByID(movieId: Long) {
        repository
            .getMovieByID(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<Movie> {
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(response: Movie) {
                        bindDetails.imageUrl = URL_IMAGE + response.posterPath
                        bindDetails.overview = response.overview
                        bindDetails.popularity = response.popularity
                        bindDetails.releaseDate = response.releaseDate
                    }
                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }
                    override fun onComplete() {
                    }
                }
            )
    }

    fun getTvShowById(movieId: Long) {
        repository
            .getTvShowById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<TvResponse> {
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(response: TvResponse) {
                        bindDetails.imageUrl = URL_IMAGE + response.posterPath
                        bindDetails.overview = response.overview
                        bindDetails.popularity = response.popularity.toString()
                        bindDetails.releaseDate = response.first_air_date
                    }
                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }
                    override fun onComplete() {
                    }
                }
            )
    }
}

