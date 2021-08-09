package com.sicoapp.movieapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id", "itemId"])
data class UserRatingsCrossRef(
    val id: String,
    @ColumnInfo(name = "itemId", index = true)
    val itemId: String
)