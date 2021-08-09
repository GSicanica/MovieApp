package com.sicoapp.movieapp.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class Rating (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "itemId")
    var itemId: String,
    @ColumnInfo(name = "rating")
    var rating: Int
) : Parcelable