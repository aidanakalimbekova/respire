package com.example.smoking.ui.theme.quest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.smoking.network.Challenge
import com.example.smoking.network.RetrofitClient.apiService
//import com.example.smoking.ui.theme.profile.Invitation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
sealed class ChallengeState {
    object Loading : ChallengeState()
    data class Success(val challengesInv: List<Challenge>, val challengesAcc:List<Challenge>) : ChallengeState()
    data class Error(val message: String) : ChallengeState()
}
class LineChartViewModel : ViewModel(){

    private val _challengeState = MutableStateFlow<ChallengeState>(ChallengeState.Loading)
    val challengeState = _challengeState.asStateFlow()
    init {
        loadChallenges()
    }

    private fun loadChallenges() {
        viewModelScope.launch {
            val invitedChallenges = mutableListOf<Challenge>()
            val acceptedChallenges = mutableListOf<Challenge>()
            var errorMessage: String? = null

            _challengeState.value = ChallengeState.Loading
            try {
                delay(500)
                val response = apiService.getChallengesList("true")
                if (response.isSuccessful) {
                    println("here i am ")
                    invitedChallenges.addAll(response.body()?.challenges ?: listOf())
                } else {
                    errorMessage = "Failed to load invited challenges"
                    println(errorMessage)
//                    _challengeState.value = ChallengeState.Error("Failed to load challenges")
                }
                val response2 = apiService.getChallengesList("false")
                if(response2.isSuccessful){
                    println("here i am hehe")
                    acceptedChallenges.addAll(response2.body()?.challenges ?: listOf())
                   }else {
//                    errorMessage = errorMessage?.plus("\nFailed to load accepted challenges") ?: "Failed to load accepted challenges"
                    val err = response2.errorBody()?.charStream()?.readText()
                    println("Error search: ${response2.code()} - $err")

//                    _challengeState.value = ChallengeState.Error("Failed to load challenges")
                }

                if (errorMessage == null) {
                    _challengeState.value = ChallengeState.Success(invitedChallenges, acceptedChallenges)
                } else {
                    _challengeState.value = ChallengeState.Error(errorMessage)
                }

            } catch (e: Exception) {
                _challengeState.value = ChallengeState.Error(e.message ?: "An unexpected error occurred")
                println("Error occurred: ${e.message}")
            }
        }
    }

    fun navigateToChallengeDetails(navController: NavController, challengeId: String) {
        navController.navigate("challengeDetails/$challengeId")
    }

}