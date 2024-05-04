package com.example.smoking.ui.theme.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.network.HttpException
import com.example.smoking.R
import com.example.smoking.ui.theme.home.Calendar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, onClick: () -> Unit, onFriends: () -> Unit, onRequests: () -> Unit){
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    when (val state = profileState) {
        is ProfileState.Loading -> {
            // Show loading indicator
            LoadingScreen()
        }
        is ProfileState.Success -> {
            val profile = state.profile
            // Display profile information
            ProfileContent(profile, onClick, onFriends, onRequests)
        }
        is ProfileState.Error -> {
            // Show error message
            ErrorScreen(state.message)
        }
    }

}

@Composable
fun ProfileContent(profile: Profile, onClick: () -> Unit, onFriends: () -> Unit, onRequests: () -> Unit){
    Column(){
        Column (horizontalAlignment = Alignment.Start){
            Column (modifier = Modifier.padding(20.dp)){
                Box(){
                    Row(){
                        Text(profile.name, fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                        Spacer(Modifier.width(150.dp))

                        AsyncImage(
                            model = profile.pic,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                    }
                }

                Text("@${profile.username}", fontSize = 15.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start)

                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically){
                    Button(
                        onClick = onFriends,
                        modifier = Modifier
                            .padding(top = 5.dp),
//                            .fillMaxWidth(),
                        contentPadding = PaddingValues(1.dp),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
                    ) {
                        Text("Friends", fontSize = 20.sp)
                    }
//                    Spacer(Modifier.width(20.dp))

                    Button(
                        onClick = onRequests,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(1.dp),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
                    ) {
                        Text("Requests", fontSize = 20.sp)
                    }

                }

                Button(onClick = onClick, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)) {
                    Text(text = "Add a friend")
                }


            }

        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = Color.Black // Customize the color as needed
        )
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(errorMessage)
    }
}