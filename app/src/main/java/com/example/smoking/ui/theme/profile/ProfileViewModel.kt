package com.example.smoking.ui.theme.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.Friend
import com.example.smoking.network.FriendX
import com.example.smoking.network.Friends
import com.example.smoking.network.Invitation
import com.example.smoking.network.RetrofitClient
import com.example.smoking.network.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
data class Profile(
    val name: String,
    val username: String,
    val pic: String,
)

data class FriendProfile(
    val name: String,
    val username: String,
    val pic: String,
)

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val profile: Profile) : ProfileState()
    data class Error(val message: String) : ProfileState()
}
class ProfileViewModel : ViewModel() {

    private val _profileState: MutableStateFlow<ProfileState> =
        MutableStateFlow(ProfileState.Loading)
    val profileState: StateFlow<ProfileState> = _profileState
    private val apiService = RetrofitClient.apiService
    private val id = Firebase.auth.currentUser!!.uid

    init {
//        _profileState.value = ProfileState.Loading
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
//            _profileState.value = ProfileState.Loading
            try {
                delay(500)
                val userProfile = getUserProfile(id)
                println("some $id")
                _profileState.value = ProfileState.Success(
                    Profile(
                        userProfile.name,
                        userProfile.username,
                        userProfile.avatar,

//                        emptyList()

                    )
                )
            } catch (e: Exception) {
                println(e.message)
                _profileState.value = ProfileState.Error(e.message ?: "An error occurred")
            }
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

}