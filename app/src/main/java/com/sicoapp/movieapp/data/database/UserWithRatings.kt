package com.sicoapp.movieapp.data.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithRatings(

    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "itemId",
        associateBy = Junction(UserRatingsCrossRef::class)
    )
    val rating: List<Rating>
)