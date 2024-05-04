package com.example.smoking

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.smoking.network.RespireApiService
import com.example.smoking.network.RetrofitClient
import com.example.smoking.network.User
import com.example.smoking.ui.theme.SmokingTheme
import com.example.smoking.ui.theme.signin.GoogleAuthUiClient
import com.example.smoking.ui.theme.signin.LoginScreen
import com.example.smoking.ui.theme.signin.UserData
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

//    private val signedInUser: UserData? = googleAuthUiClient.getSignedInUser()
//    private val userId: String? = signedInUser?.userId
//    println("userId $userId")

    private val apiService = RetrofitClient.apiService


    private val firebaseAuth = FirebaseAuth.getInstance()
    fun getUserInfo(): Flow<FirebaseUser?> =
        callbackFlow {
//            googleAuthUiClient.signOut()
            println("getUserInfo")
            val authStateListener = FirebaseAuth.AuthStateListener {
                trySendBlocking(it.currentUser)
            }
            firebaseAuth.addAuthStateListener(authStateListener)
            awaitClose {
                firebaseAuth.removeAuthStateListener(authStateListener)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            println("oncreate")
            getUserInfo()

            LaunchedEffect(Unit) {
                getUserInfo().collect { user ->
                    if (user != null) {
                        println("Debug: User collected via getUserInfo -> $user")
                        onLoginSuccess()
                    }
                }
            }
            SmokingTheme {
                LoginScreen({ onLoginSuccess() }, googleAuthUiClient)
            }
        }
    }

    private fun onLoginSuccess() {
        lifecycleScope.launch {

//            googleAuthUiClient.signOut()
//            googleAuthUiClient.signOut()
            println("onloginsuccess")
//            delay(2000)
//            println("hmm, ${FirebaseAuth.getInstance().currentUser}")


            val user = getUser()

            // Explicitly switch context
            println("haha")
//            delay(2000)
            if (user?.status == "created") {
                println("what is happening")
                val intent = Intent(this@LoginActivity, WelcomeScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                println("what is created")
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private suspend fun getUser(): User? {
        return try {
            val response = apiService.getUser()
            if (response.isSuccessful) {
                println("API success: ${response.body()}")
                response.body()
            } else {
                println("API call failed: ${response.errorBody()}")
                null
            }
        } catch (e: Exception) {
            println("API call exception: ${e.message}")
            null
        }
    }
}
