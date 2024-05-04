package com.example.smoking.network

data class Invitations(
    val invitations: List<Invitation>
)

data class InvHandle(
    val accept: Boolean,
    val invitation_id: String,
)