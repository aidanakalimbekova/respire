package com.example.smoking.network

import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("avatar")val avatar: String,
    @SerializedName("id")val id: String,
    @SerializedName("name")val name: String,
    @SerializedName("username")val username: String
)



