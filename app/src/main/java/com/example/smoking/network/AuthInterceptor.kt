package com.example.smoking.network

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenRepository: TokenRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = runBlocking {
            // Subscription might be needed if using SharedFlow
            tokenRepository.token.first { it != null }
        }

        println("Token in Interceptor: $token")  // Debug printing

        val requestBuilder = original.newBuilder().apply {
            token?.let {
                header("Authorization", it)
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}