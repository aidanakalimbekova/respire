package com.example.smoking.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RespireApiService {
    @GET("user")
    suspend fun getUser(
    ): Response<User>

    @GET("user/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: String,
    ): Response<User>

    @GET("user/search")
    suspend fun getUsers(
        @Query("username") username: String,
        @Query("limit") limit: Int
    ): Response<UsersSearch>


    @PUT("user/{userId}")
    suspend fun putUser(@Path("userId") userId: String, @Body user: UserPut): Response<Unit>

    @GET("friends")
    suspend fun getFriends(
    ): Response<Friends>
//
    @GET("friends/invitations")
    suspend fun getInvitations(
    ): Response<Invitations>

    @POST("friends/invitations")
    suspend fun createInvitation(
        @Body user: InvitationRequest
    ): Response<Unit>

    @POST("friends/invitations/handle")
    suspend fun handleInvitation(
        @Body user: InvHandle
    ): Response<Unit>

    @POST("challenges")
    suspend fun postChallenge(
        @Body challenge: ChallengePut
    ): Response<Unit>

    @GET("challenges")
    suspend fun getChallengesList(
        @Query("invite") invite: String
    ): Response<Challenges>

    @GET("challenges/{id}")
    suspend fun getChallenge(
        @Path("id") id: String
    ): Response<ChallengeX>

    @POST("sessions")
    suspend fun postSessions(
        @Body challenge: SessionPut
    ): Response<Unit>

    @GET("sessions/by_time")
    suspend fun getSessions(
        @Query("start") start: String,
        @Query("end") end: String,
    ): Response<Int>

    @GET("sessions/stat")
    suspend fun getStat(): Response<Stat>

}