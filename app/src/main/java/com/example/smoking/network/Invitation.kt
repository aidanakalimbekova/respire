package com.example.smoking.network

data class Invitation(
    val from_user_id: String,
    val id: String,
    val sent_date: String,
    val status: String,
    val to_user_id: String
)