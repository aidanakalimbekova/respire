package com.example.smoking.network

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object TokenProvider {
    suspend fun getToken(): String? {
        return try {
            Firebase.auth.currentUser?.getIdToken(true)?.await()?.token
        } catch (e: Exception) {
            null
        }
    }
}