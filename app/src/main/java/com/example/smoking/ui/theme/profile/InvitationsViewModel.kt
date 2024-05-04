package com.example.smoking.ui.theme.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.InvHandle
import com.example.smoking.network.Invitation
import com.example.smoking.network.RetrofitClient.apiService
import com.example.smoking.network.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InvitationsViewModel: ViewModel()  {

    var invitationProfiles = mutableStateOf<List<InvitationProfile>>(listOf())
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    init {
        loadInvitations()
    }
    private fun loadInvitations() {
        viewModelScope.launch {
            isLoading.value = true
            delay(500)
            try {
                val invitations = getUserInvitations()
                invitationProfiles.value = processInvitations(invitations)
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Failed to load invitations"
            } finally {
                isLoading.value = false
            }
        }
    }
    fun handleInvitation(accept: Boolean, invitationId: String) {
        viewModelScope.launch {
            try {
                val response = apiService.handleInvitation(InvHandle(accept, invitationId))
                if (response.isSuccessful) {
                    // Reload or update the current invitationList based on the server's response.
                    loadInvitations()
                } else {
                    throw Exception("Failed to perform invitation operation")
                }
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Operation failed"
            }
        }
    }
    private suspend fun getUserInvitations(): List<Invitation> {
        val response = apiService.getInvitations()
        if (response.isSuccessful) {

            return response.body()?.invitations ?: listOf()
        } else {
//            println("API call failed: ${response.errorBody()}")
            val errorMessage = response.errorBody()?.charStream()?.readText()
            println("Error: ${response.code()} - $errorMessage")
//            println("Failed inv")
            throw Exception("Failed to fetch user invitations")
        }
    }

    private suspend fun getUserProfile(idUser: String): User {
        val response = apiService.getUserById(idUser)
        println("getUserProfile ${response.body()}")
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response")
        } else {
            println("Failed user prof")
            throw Exception("Failed to fetch user profile")
        }
    }
    private suspend fun processInvitations(invitations: List<Invitation>): List<InvitationProfile> {
        val processedInvitations = mutableListOf<InvitationProfile>()
        invitations.forEach { invitation ->
            try {
                val user = getUserProfile(invitation.from_user_id)
                processedInvitations.add(
                    InvitationProfile(
                        user.avatar,
                        user.name,
                        user.username,
                        invitation.id
                    )
                )
            } catch (e: Exception) {

                println("Failed process")
            }
        }
        return processedInvitations
    }
}


data class InvitationProfile(
    val avatar: String,
    val name: String,
    val username: String,
    val invitationId: String
)

