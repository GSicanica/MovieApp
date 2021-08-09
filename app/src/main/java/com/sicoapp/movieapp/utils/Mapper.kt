package com.sicoapp.movieapp.utils

import com.sicoapp.movieapp.data.database.User
import com.sicoapp.movieapp.data.remote.firebase.model.UserFirebase
import com.sicoapp.movieapp.data.remote.response.user.UserModel

fun UserFirebase.mapToUserEntity() : User {
    return  User(
        id = this.id,
        name = this.name,
        email = this.email,
        image = this.image,
        movieId = this.movieId,
        movieRating = this.movieRating,
        fcmToken = this.fcmToken
    )
}

fun UserFirebase.mapToUserModel() : UserModel {
    return  UserModel(
        id = this.id,
        name = this.name,
        email = this.email,
        image = this.image,
        movieId = this.movieId,
        movieRating = this.movieRating,
        fcmToken = this.fcmToken
    )
}
