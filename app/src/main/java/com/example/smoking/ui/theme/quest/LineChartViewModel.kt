package com.example.smoking.ui.theme.quest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.Challenge
//import com.example.smoking.ui.theme.profile.Invitation
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
data class Challenge1(
    val id: String,
    val title: String,
    val daysLeft: Int,
    val invitedBy: String? = null,
    val status: ChallengeStatus
)

enum class ChallengeStatus {
    INVITED, ACCEPTED
}
class LineChartViewModel : ViewModel(){

    private val _challenges = mutableStateOf<List<Challenge1>>(listOf())
    val challenges: State<List<Challenge1>> = _challenges

    init {
        loadChallenges()
    }

    private fun loadChallenges() {
        // Placeholder for data loading logic
        _challenges.value = listOf(
            Challenge1("1", "Forest Trek", 5, "John Doe", ChallengeStatus.INVITED),
            Challenge1("2", "Hill Climbing", 3, "Emily Ross", ChallengeStatus.INVITED),
            Challenge1("3", "River Crossing", 2, status = ChallengeStatus.ACCEPTED)
        )
    }

    fun handleChallengeAction(challengeId: String) {
        // Placeholder implementation
    }



}