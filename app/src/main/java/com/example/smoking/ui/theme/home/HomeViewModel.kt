package com.example.smoking.ui.theme.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.GeminiPut
import com.example.smoking.network.RetrofitClient
import com.example.smoking.network.SessionPut
import com.example.smoking.network.User
import com.example.smoking.ui.theme.profile.Profile
import com.example.smoking.ui.theme.profile.ProfileState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class HomeViewModel : ViewModel() {
    private val d = LocalDate.now()
    private val r = formTime(d)
    private val _selectedDay = mutableStateOf(Time(d, r[0], r[1], "Today", "", ""))
    val selectedDay: State<Time> = _selectedDay
    private val _profileState: MutableStateFlow<Profile> =
        MutableStateFlow<Profile>(Profile("", "", ""))
    val profileState: StateFlow<Profile> = _profileState
    private val apiService = RetrofitClient.apiService
    private val _counterValue = MutableStateFlow(0)
    val counterValue = _counterValue.asStateFlow()

    init {
//        _profileState.value = ProfileState.Loading
        fetchUserProfile()
        getCounter()
    }
    fun updateCounter(increment: Int) {
        if(_counterValue.value == 0 && increment == -1){
            _counterValue.value = 0
        }else{
            _counterValue.value += increment
        }
    }
    private fun getCounter(){
        val today = LocalDate.now()
        val startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant().toString()
        val endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toString()

        viewModelScope.launch {
            try {
                val response = apiService.getSessions(startOfDay, endOfDay)
                if (response.isSuccessful) {
                    println("API success: ${response.body()}")
                    _counterValue.value = response.body()!!
                } else {
//                    _counterValue.value = 0
                    println("API call failed: ${response.errorBody()}")
                }

            } catch (e: Exception) {
                println(e.message)

            }
        }
    }

    fun postRec(craving: Int, mood: String, context: String ) {
        viewModelScope.launch {
            try {
                println("Here I am")
                val res = RetrofitClient.apiService.postRecommendations(
                    GeminiPut(craving, mood, context)
                )
                if (res.isSuccessful) {
                    println("ok")
                } else {
                    val errorMessage = res.errorBody()?.charStream()?.readText()
                    println("Error search: ${res.code()} - $errorMessage")
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun saveCounterValue() {
        println("Saving counter value: ${_counterValue.value}")
        viewModelScope.launch {
//            _profileState.value = ProfileState.Loading
            try {
                println("Here I am")
                val res = apiService.postSessions(SessionPut(_counterValue.value, DateTimeFormatter.ISO_INSTANT.format(
                    Instant.now()), ""))
                if (res.isSuccessful) {
                    println("ok")
                } else {
                    val errorMessage = res.errorBody()?.charStream()?.readText()
                    println("Error search: ${res.code()} - $errorMessage")

                }
            } catch (e: Exception) {
                println(e.message)
                _profileState.value = Profile("", "", "")
            }
        }
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
//            _profileState.value = ProfileState.Loading
            try {
                val userProfile = getUserProfile()
                _profileState.value = Profile(
                    userProfile.name,
                    userProfile.username,
                    userProfile.avatar,
                )
            } catch (e: Exception) {
                println(e.message)
                _profileState.value = Profile("", "", "")
            }
        }
    }

    private suspend fun getUserProfile(): User {
        val response = apiService.getUser()
        println("getUserProfile ${response.body()}")
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response")
        } else {
            println("Failed user prof")
            throw Exception("Failed to fetch user profile")
        }
    }

}




private fun formTime(d: LocalDate): List<String> {
    val formatDay = d.format(DateTimeFormatter.ofPattern("dd EEE"))
    return formatDay.split(" ")
}
data class Time(val localDate: LocalDate, val day: String, val dayOfWeek:String, val label: String, val counter: Any?, val streak: String)