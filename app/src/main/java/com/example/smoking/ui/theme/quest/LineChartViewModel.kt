package com.example.smoking.ui.theme.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.ui.theme.profile.Invitation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LineChartViewModel : ViewModel(){

    private val _state = MutableStateFlow<List<Friend>>(emptyList())
    val state: StateFlow<List<Friend>> = _state.asStateFlow()

    val auth = Firebase.auth
    val curUser = auth.currentUser

    private val db = Firebase.firestore

    private lateinit var functions: FirebaseFunctions

    init {
        friendsQuest("")
    }
    private fun friendsQuest(friendID: String): Task<List<Map<String, Any>>> {
        functions = Firebase.functions
        // Create the arguments to the callable function.
        val data = hashMapOf(
            "friendId" to friendID
        )
        return functions
            .getHttpsCallable("friendsQuest")
            .call(data)
            .continueWith { task ->
                val result = task.result?.data as Map<String, Any>
                val success = result["success"] as Boolean
                val messageList = result["message"] as List<Map<String, Any>>
                messageList
            }.addOnSuccessListener { messages ->
                val l = mutableListOf<Friend>()
                messages.forEach {
                    item->
                    val name = item["name"] as String
                    val smoked = item["smoked"] as Int
                    val streak = item["streak"] as Int
                    val photo = item["photo"] as String
                    l.add(Friend(name, smoked, streak,  photo))
                    println("Name: $name, Smoked: $smoked, Streak: $streak")
                }
                l.sortBy { it.smoked }
                if (l != _state.value) {
                    _state.value = l
                }
            }
    }

    data class Friend(val name:String, val smoked:Int, val streak:Int, val photo:String)

}