package com.example.smoking.ui.theme.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.InvitationRequest
import com.example.smoking.network.RetrofitClient.apiService
import com.example.smoking.network.UserX
import kotlinx.coroutines.launch

class AddFriendViewModel: ViewModel() {

    var users = mutableStateOf<List<UserX>>(listOf())
        private set  // Make it read-only from outside

    var isLoading = mutableStateOf(false)
        private set  // Make it read-only from outside

    var invitationStatus = mutableStateOf<Map<String, Boolean>>(mapOf())
        private set

    fun searchUsers(username: String) {
        viewModelScope.launch {
            isLoading.value = true
            val response = apiService.getUsers(username, 5)  // Assume "10" is the desired limit
            if (response.isSuccessful) {
                users.value = response.body()?.users ?: listOf()
            } else {

                val errorMessage = response.errorBody()?.charStream()?.readText()
                println("Error search: ${response.code()} - $errorMessage")
                // Optionally handle errors
            }
            isLoading.value = false
        }
    }

    fun sendInvitation(userId: String) {
        viewModelScope.launch {
            isLoading.value = true
            val response = apiService.createInvitation(InvitationRequest(userId))
            isLoading.value = false
            if (response.isSuccessful) {
                println("great")
                val currentStatus = invitationStatus.value.toMutableMap()
                currentStatus[userId] = true
                invitationStatus.value = currentStatus
                // Handle successful invitation, notify user if necessary
            } else {
                println("no inv")
                val errorMessage = response.errorBody()?.charStream()?.readText()
                println("Error: ${response.code()} - $errorMessage")
                // Optionally handle errors
            }
        }
    }


}