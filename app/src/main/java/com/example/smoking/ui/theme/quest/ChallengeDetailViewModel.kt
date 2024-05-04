package com.example.smoking.ui.theme.quest

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.Challenge
import com.example.smoking.network.ChallengeX
import com.example.smoking.network.RetrofitClient.apiService
import com.example.smoking.ui.theme.profile.ProfileState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChallengeDetailViewModel (challengeId : String) : ViewModel() {
    private val _challengeState: MutableStateFlow<ChallengesState> =
        MutableStateFlow(ChallengesState.Loading)
    val challengeState = _challengeState.asStateFlow()

    init {
        loadChallengeDetail(challengeId)
    }
    private fun loadChallengeDetail(id: String) {
        viewModelScope.launch {
            println("soooo")
            delay(500)
//            _challengeState.value = ChallengesState.Loading
            try {
                val response = apiService.getChallenge(id)
                if (response.isSuccessful) {
                    println("${response.body()}")
                    _challengeState.value = ChallengesState.Success(response.body())
                } else {
                    _challengeState.value = ChallengesState.Error("Failed to load challenge details")
                }
            } catch (e: Exception) {
                _challengeState.value = ChallengesState.Error(e.message ?: "Unknown error")
            }
        }
    }

}
sealed class ChallengesState {
    object Loading : ChallengesState()
    data class Success(val challenge: ChallengeX?) : ChallengesState()
    data class Error(val message: String) : ChallengesState()
}