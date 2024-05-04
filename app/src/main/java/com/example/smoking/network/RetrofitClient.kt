package com.example.smoking.network

import com.google.firebase.firestore.core.UserData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

object RetrofitClient {
    private const val BASE_URL = "https://respire-api-go-jc4tvs5hja-ey.a.run.app/api/v1/"
    private val tokenRepository = TokenRepository()
    private val authInterceptor = AuthInterceptor(tokenRepository)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: RespireApiService by lazy {
        retrofit.create(RespireApiService::class.java)
    }
}
