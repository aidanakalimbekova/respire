package com.example.smoking.network

data class ChallengeX(
    val cigarettes_limit: Int,
    val created_at: String,
    val description: String,
    val end_date: String,
    val id: String,
    val invited: List<String>,
    val leaderboard: List<Leaderboard>,
    val name: String,
    val owner_id: String,
    val participants: List<String>,
    val penalty: Int,
    val prize: String,
    val type: String,
    val updated_at: String
)