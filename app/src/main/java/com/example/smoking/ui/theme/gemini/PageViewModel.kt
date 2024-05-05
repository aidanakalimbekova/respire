package com.example.smoking.ui.theme.gemini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.network.GeminiPut
import com.example.smoking.network.Recommendations
import com.example.smoking.network.RetrofitClient.apiService
import com.example.smoking.network.SessionPut
import com.example.smoking.network.User
import com.example.smoking.ui.theme.profile.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.format.DateTimeFormatter

class PageViewModel : ViewModel() {

    private val _rec = MutableStateFlow<List<String>>(listOf())
    val rec = _rec.asStateFlow()

    init {
        getRec()
    }
    private fun getRec() {
        viewModelScope.launch {
            try {
                val response = apiService.getRecommendations(GeminiPut(0, "", ""))
                if (response.isSuccessful) {
                    println("API success: ${response.body()}")
                    _rec.value = response.body()?.reccomendations!!
                } else {
                    val errorMessage = response.errorBody()?.charStream()?.readText()
                    println("Error: ${response.code()} - $errorMessage")

//                    println("API call failed: ${response.errorBody()}")
                    _rec.value = emptyList()
                }
            } catch (e: Exception) {
                println("API call exception: ${e.message}")
            }
        }
    }

}
