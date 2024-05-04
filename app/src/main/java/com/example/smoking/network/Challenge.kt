package com.example.smoking.network

data class Challenge(
    val cigarettes_limit: Int,
    val description: String,
    val end_date: String,
    val id: String,
    val invited: List<String>,
    val name: String,
    val owner_id: String,
    val participants: List<String>,
    val penalty: Int,
    val prize: String,
    val type: String
)