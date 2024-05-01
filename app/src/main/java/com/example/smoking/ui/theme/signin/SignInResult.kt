package com.example.smoking.ui.theme.signin

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GetTokenResult


data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?,
    var token: String
)