package com.example.smoking.network

import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("avatar")val avatar: String,
    @SerializedName("id")val id: String,
    @SerializedName("name")val name: String,
    @SerializedName("username")val username: String
)

data class Invitation(
    @SerializedName("from_user_id")val fromUserId: String,
    @SerializedName("id")val id: String,
    @SerializedName("sent_date")val sentDate: String,
    @SerializedName("status")val status: String,
    @SerializedName("to_user_id") val toUserId: String
)


