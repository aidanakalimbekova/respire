package com.example.smoking.network

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("email") val email: String,
    @SerializedName("friends") val friends: Int,
    @SerializedName("id") val id: String,
    @SerializedName("longest_streak") val longestStreak: Int,
    @SerializedName("name") val name: String,
    @SerializedName("quit_date") val quitDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("username") val username: String
)

data class UserPut(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("name") val name: String,
    @SerializedName("quit_date") val quitDate: String,
    @SerializedName("username") val username: String
)