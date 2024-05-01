package com.example.smoking

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
//import androidx.core.app.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.smoking.network.RespireApiService
import com.example.smoking.network.RetrofitClient
import com.example.smoking.network.UserPut
import com.example.smoking.ui.theme.signin.GoogleAuthUiClient
import com.example.smoking.ui.theme.signin.WelcomeScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class WelcomeScreenActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeScreen { username, quitDate ->
                // Handle posting data to backend here
                // After posting data, navigate to MainActivity

                lifecycleScope.launch {

                    val id = googleAuthUiClient.getSignedInUser()!!.userId
                    val response = apiService.putUser(id, UserPut("", "", quitDate, username))
//                    val response = apiService.putUser(UserPut("", "", quitDate, username))
                    if (response.isSuccessful) {

                        val intent = Intent(this@WelcomeScreenActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val errorMessage = response.errorBody()?.charStream()?.readText()
                        println("Error: ${response.code()} - $errorMessage")
                        // Handle error scenario
                        Toast.makeText(applicationContext, "Failed to update user data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}