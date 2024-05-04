package com.example.smoking.network

data class UsersSearch(
    val users: List<UserX>
)

data class InvitationRequest(
    val friend_id: String
)