package com.sicoapp.movieapp.ui.saved

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.UserWithRatings
import com.sicoapp.movieapp.data.remote.response.movie.Movie
import com.sicoapp.movieapp.domain.Repository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SavedViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    val adapter = SavedAdapter()
    var allElement = mutableListOf<Movie>()

    suspend fun getRatingsOfUser(curenntUSer :String): List<UserWithRatings> {
        return repository.getRatingsOfUser(curenntUSer)
    }
    
    fun getByMovieID(list: List<UserWithRatings>) {

        list.map {
            val listOfRatingsForCurrentUserFromDB = it.rating

            for (element in listOfRatingsForCurrentUserFromDB) {
                val singleMovie =
                    repository
                        .getMovieByID(element.itemId.toLong())

                singleMovie
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        object : Observer<Movie> {
                            override fun onSubscribe(d: Disposable) {
                            }
                            override fun onNext(response: Movie) {
                                allElement.add(response)
                                adapter.addMovieWithRating(allElement, listOfRatingsForCurrentUserFromDB)
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
    }
}


