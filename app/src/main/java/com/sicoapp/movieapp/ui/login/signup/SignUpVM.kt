package com.sicoapp.movieapp.ui.login.signup

import android.text.TextUtils
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.database.User
import com.sicoapp.movieapp.data.remote.firebase.model.UserFirebase
import com.sicoapp.movieapp.domain.Repository
import com.sicoapp.movieapp.utils.mapToUserEntity

class SignUpVM @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private fun insertTokenFromCurrentUserIntoDB(userDB: User) {
        repository
            .insertUser(userDB)
    }

    fun validateForm(name: String, email: String, password: String): String {
        return when {
            TextUtils.isEmpty(name) -> {
                "falseName"
            }
            TextUtils.isEmpty(email) -> {
                "falseEmail"
            }
            TextUtils.isEmpty(password) -> {
                "false"
            }
            else -> {
                "true"
            }
        }
    }

    fun insertInDB(userFirebase: UserFirebase) {
        insertTokenFromCurrentUserIntoDB(userFirebase.mapToUserEntity())
    }
}