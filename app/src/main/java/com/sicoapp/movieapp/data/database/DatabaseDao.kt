package com.sicoapp.movieapp.data.database

import androidx.room.*
import io.reactivex.Observable

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSmiley(rating: Rating)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    fun getAuthUserDB(): Observable<List<User>>

    @Query("SELECT * FROM Rating WHERE itemId =:itemId")
    fun getSmileyByMovieId(itemId: Int): Observable<Rating>

    @Query("DELETE FROM Rating  WHERE itemId =:itemId")
    suspend fun deleteSmileyByMovieId(itemId: Int)

    @Transaction
    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getRatingsOfUser(id: String): List<UserWithRatings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: UserRatingsCrossRef)

}