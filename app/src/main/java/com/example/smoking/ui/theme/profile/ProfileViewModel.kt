package com.example.smoking.ui.theme.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel(){

//    private val _state = mutableStateOf(Profile(mutableListOf(), mutableListOf()))
//    val state: State<Profile> =_state

    private val _state = MutableStateFlow<List<Invitation>>(emptyList())
    val state: StateFlow<List<Invitation>> = _state.asStateFlow()

    val auth = Firebase.auth
    val curUser = auth.currentUser

    private val db = Firebase.firestore

    private lateinit var functions: FirebaseFunctions

    init {
        listOfInvitations()
    }
    private fun inviteFriend(friendID: String):Task<String>{
        functions = Firebase.functions
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "friendId" to friendID
        )

        return functions
            .getHttpsCallable("inviteFriend")
            .call(data)
            .continueWith { task ->
            // This continuation runs on either success or failure, but if the task
            // has failed then result will throw an Exception which will be
            // propagated down. Here we are casting the result to a Map if the task is successful.
            val result = task.result?.data as Map<String, Any>
            // Assuming the cloud function returns a message as a part of the response
            // under the key "message". Make sure to align with what your cloud function returns.
            result["success"] as Boolean
            result["message"] as String
        }

    }

     fun invite(friendID: String){
        inviteFriend(friendID).addOnCompleteListener {
            if (!it.isSuccessful) {
                val e = it.exception
                println("$e")
            }
        }
    }


    fun listOfInvitations()  {
        var userId: String = ""
        curUser?.run {
            userId = uid
        }
        val userDetailsList = mutableListOf<Invitation>()
        var t  = mutableListOf<String>()
        viewModelScope.launch {
            // Perform the query
            val temp = db.collection("users").document(userId)
                .get().await()
            t = temp.data?.get("invitations") as MutableList<String>
            Log.d("Document data:", "$t")
            t.forEach { invitedUserId ->
                val invitedUserDoc = db.collection("users").document(invitedUserId).get().await()
                val name = invitedUserDoc.data?.get("name") as? String ?: "Unknown"
                val photoUrl = invitedUserDoc.data?.get("photoUrl") as? String ?: "No Photo"
                // Add the fetched details to the list
                userDetailsList.add(Invitation(invitedUserId, name, photoUrl))
            }

            if (userDetailsList != _state.value) {
                _state.value = userDetailsList
            }

            // Now userDetailsList contains all the fetched names and photoUrls
            // Do something with userDetailsList, like updating UI or ViewModel state
            Log.d("Invitations Details", "$userDetailsList")
        }

    }

    fun handleInvitation(accept: Boolean, friendId: String): Task<String> {
        functions = Firebase.functions
        val data = hashMapOf(
            "accept" to accept,
            "friendId" to friendId
        )

        return functions
            .getHttpsCallable("handleInvitation")
            .call(data)
            .continueWith { task ->
                // Extract the response data and return the message
                val result = task.result?.data as Map<String, Any>
                result["success"] as Boolean
                result["message"] as String
            }
    }

    fun handleInv(accept: Boolean, friendID: String){

        handleInvitation(accept, friendID).addOnCompleteListener {
            if (!it.isSuccessful) {
                val e = it.exception
                println("$e")
            } else{
               removeItem(friendID)}
        }
    }
    fun removeItem(friendID: String) {
//        state.value.invitationList.filter { it.friendId !=friendID }
//        val t = state.value.invitationList
//        _state.value = state.value.copy(invitationList = t)
        val l = _state.value.toMutableList()
        l.removeIf { it.friendId == friendID }
        _state.value = l
    }

}

//data class Profile(var invitationList: MutableList<Invitation>, val friendsList:MutableList<Friend>)


data class Invitation(val friendId: String, val name:String, val photoUrl:String)