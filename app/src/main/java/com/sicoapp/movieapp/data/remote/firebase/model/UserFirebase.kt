package com.sicoapp.movieapp.data.remote.firebase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserFirebase(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val image: String = "",
    val movieId: String = "",
    val movieRating: String = "",
    val fcmToken: String = ""
) : Parcelable
