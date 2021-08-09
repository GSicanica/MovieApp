package com.sicoapp.movieapp.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "image")
    var image: String?,
    @ColumnInfo(name = "movieId")
    var movieId: String?,
    @ColumnInfo(name = "movieRating")
    var movieRating: String?,
    @ColumnInfo(name = "fcmToken")
    var fcmToken: String?
) : Parcelable

