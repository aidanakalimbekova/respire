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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
data class Profile(
    val name: String,
    val username: String,
    val pic: String,
    val listFriends: List<FriendProfile>,
    val invitations: List<InvitationProfile>,
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
class ProfileViewModel : ViewModel(){

    private val _profileState: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState.Loading)
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
                val userProfile = getUserProfile(id)
                println("some $id")
                val userFriends = getUserFriends()
                println("some ll ${userProfile.avatar}")
//                val userInvitations = getUserInvitations()
//                println("some rr $userInvitations")
                _profileState.value = ProfileState.Success(
                    Profile(
                        userProfile.name,
                        userProfile.username,
                        userProfile.avatar,
                        userFriends.map { friend ->
                            FriendProfile(
                                friend.name,
                                friend.username,
                                friend.avatar
                            )
                        },
                        emptyList()
//                        processInvitations(userInvitations)
                    )
                )
            } catch (e: Exception) {
                println(e.message)
                _profileState.value = ProfileState.Error(e.message ?: "An error occurred")
            }
        }
    }

    private suspend fun getUserProfile(idUser: String): User {
//        val id = Firebase.auth.currentUser!!.uid
        val response = apiService.getUserById(idUser)
        println("getUserProfile ${response.body()}")
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response")
        } else {
            println("Failed user prof")
            throw Exception("Failed to fetch user profile")
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

    private suspend fun getUserInvitations(): List<Invitation> {
        val response = apiService.getInvitations()
        if (response.isSuccessful) {
            return response.body() ?: listOf()
        } else {
            println("Failed inv")
            throw Exception("Failed to fetch user invitations")
        }
    }

    private suspend fun processInvitations(invitations: List<Invitation>): List<InvitationProfile> {
        val processedInvitations = mutableListOf<InvitationProfile>()
        invitations.forEach { invitation ->
            try {
                val user = getUserProfile(invitation.fromUserId)
                processedInvitations.add(
                    InvitationProfile(
                        user.avatar,
                        user.name,
                        user.username
                    )
                )
            } catch (e: Exception) {
                // Handle error fetching user details for invitation
                println("Failed process")
            }
        }
        return processedInvitations
    }

}


data class InvitationProfile(
    val avatar: String,
    val name: String,
    val username: String
)
