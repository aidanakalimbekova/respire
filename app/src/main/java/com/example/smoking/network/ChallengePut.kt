package com.example.smoking.network

data class ChallengePut(
    val cigarettes_limit: Int,
    val description: String,
    val end_date: String,
    val invited: List<String>,
    val name: String,
    val penalty: Int,
    val prize: String,
    val type: String
)