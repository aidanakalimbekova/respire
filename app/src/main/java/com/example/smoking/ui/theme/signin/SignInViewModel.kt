package com.example.smoking.ui.theme.signin


import android.content.Intent
import android.net.http.NetworkException
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smoking.MainActivity
import com.example.smoking.WelcomeScreenActivity
import com.example.smoking.network.RetrofitClient
import com.example.smoking.network.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()
    private val apiService = RetrofitClient.apiService
    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )

        }
//        onClick()
        println("what is sign in $result")
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}