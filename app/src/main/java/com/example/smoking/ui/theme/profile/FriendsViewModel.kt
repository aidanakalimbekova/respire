package com.example.smoking.ui.theme.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.FriendX
import com.example.smoking.network.InvitationRequest
import com.example.smoking.network.RetrofitClient.apiService
import com.example.smoking.network.UserX
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FriendsViewModel: ViewModel() {
    var friends = mutableStateOf<List<FriendX>>(listOf())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf("")

    init {
        fetchFriends()
    }
    private fun fetchFriends(){
        viewModelScope.launch {
            isLoading.value = true
            delay(500)
            try {
                friends.value = getUserFriends()
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Unknown error"
            } finally {
                isLoading.value = false
            }
        }
    }
    private suspend fun getUserFriends(): List<FriendX> {
        val response = apiService.getFriends()
        println("some lo ${response.body()}")
        if (response.isSuccessful) {
//            println("some lo ${response.body()}")
            return response.body()?.friends ?: listOf()
        } else {
            println("Failed")
            throw Exception("Failed to fetch user friends")
        }
    }

}