package com.example.smoking

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.example.smoking.ui.theme.SmokingTheme
import com.example.smoking.ui.theme.signin.GoogleAuthUiClient
import com.example.smoking.ui.theme.signin.LoginScreen
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun getUserInfo(): Flow<FirebaseUser?> =
        callbackFlow {
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
//            firebaseAuth.signOut()
            getUserInfo()
            if(googleAuthUiClient.getSignedInUser() != null) {
                onLoginSuccess()
            }
            SmokingTheme {
                LoginScreen ({ onLoginSuccess() }, googleAuthUiClient )
            }
        }
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
