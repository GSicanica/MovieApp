package com.sicoapp.movieapp.data.database

import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class DataBaseDataSource @Inject constructor(
    private val dao: DatabaseDao
) {

    fun insertSmiley(itemId: Int, rating: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val rating = Rating(itemId.toString(), rating)
            dao.insertSmiley(rating)
        }
    }

    suspend fun getRatingsOfUser(currentUser : String): List<UserWithRatings> {
        return dao.getRatingsOfUser(currentUser)
    }

    fun insertUser(
        user: User
    )
    {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertUser(user)
        }
    }

    fun getAuthUserDB(): Observable<List<User>> {
        return dao.getAuthUserDB()
    }

    fun getSmileyByMovieId(itemId: Int): Observable<Rating> {
        return dao.getSmileyByMovieId(itemId)
    }


    fun deleteSmileyByMovieId(itemId: Int) {
       CoroutineScope(Dispatchers.IO).launch {
           dao.deleteSmileyByMovieId(itemId)
       }
    }

    suspend fun insertStudentSubjectCrossRef(crossRef : UserRatingsCrossRef){
        dao.insertStudentSubjectCrossRef(crossRef)
    }
}