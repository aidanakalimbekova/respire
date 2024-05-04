package com.example.smoking.network

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class TokenRepository {
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    private val job = CoroutineScope(Dispatchers.IO).launch {
        while (isActive) {
            fetchToken()
            delay(TimeUnit.HOURS.toMillis(1))  // Refresh every hour
        }
    }

    init {
        fetchToken()  // Initial fetch
    }

    private fun fetchToken() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val newToken = FirebaseAuth.getInstance().currentUser?.getIdToken(true)?.await()?.token
                _token.value = newToken
//                println(newToken)
            } catch (e: Exception) {
                // Handle exceptions, possibly with exponential backoff
            }
        }
    }

    fun forceRefresh() {
        fetchToken()
    }

    fun cleanup() {
        job.cancel()
    }
}