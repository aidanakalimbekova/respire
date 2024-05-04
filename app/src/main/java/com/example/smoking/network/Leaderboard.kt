package com.example.smoking.network

data class Leaderboard(
    val avatar: String,
    val id: String,
    val name: String,
    val position: Int,
    val smoke_count: Int,
    val username: String
)