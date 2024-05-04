package com.example.smoking.ui.theme.profile

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.network.HttpException
import com.example.smoking.R
import com.example.smoking.ui.theme.statistics.HealthCard
import com.example.smoking.ui.theme.statistics.StatisticCard
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(viewModel: ProfileViewModel){
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    when (val state = profileState) {
        is ProfileState.Loading -> {
            // Show loading indicator
            LoadingScreen()
        }
        is ProfileState.Success -> {
            val profile = state.profile
            // Display profile information
            ProfileContent(profile)
        }
        is ProfileState.Error -> {
            // Show error message
            ErrorScreen(state.message)
        }
    }

}


@Composable
fun ProfileContent(profile: Profile){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
//        .background(
//        brush = Brush.verticalGradient(
//            colors = listOf(
//                Color(0xFF93F1F7),
//                Color(0xFFF6DF67),
//            )
//        )
//    )
        ){
        Column (horizontalAlignment = Alignment.Start){
            Column (modifier = Modifier.padding(20.dp)){
                Box(){
                    Row(){
                        Text(profile.name, fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
//                        Spacer(Modifier.width(150.dp))

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

                Text(profile.username, fontSize = 15.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start)

                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Add a friend")
                }
            }
        }

        //Statistics
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
//        .background(
//            brush = Brush.verticalGradient(
//                colors = listOf(
//                    Color(0xFF93F1F7),
//                    Color(0xFFF6DF67),
//                )
//            )
//        ),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(){
                StatisticCard()
                StatisticCard()
                StatisticCard()
            }
            com.example.smoking.ui.theme.statistics.CigaretteProgressBox(progress = 0.7f)

            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Health Improvements",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Column(modifier = Modifier.padding(5.dp)){
                Row(){
                    HealthCard(title = "Progress", progress = 0.75f)
                    HealthCard(title = "Progress", progress = 0.75f)
                }
                Row(){
                    HealthCard(title = "Progress", progress = 0.75f)
                    HealthCard(title = "Progress", progress = 0.75f)
                }
                Row(){
                    HealthCard(title = "Progress", progress = 0.75f)
                    HealthCard(title = "Progress", progress = 0.75f)
                }
            }
        }
    }
}

@Composable
fun ListFriends(){
    LazyColumn(){
//        items(s, key = { it.friendId }) { invitation ->
//            Box(){
//                Row (modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
//                    AsyncImage(
//                        model = invitation.photoUrl,
//                        contentDescription = "Profile picture",
//                        modifier = Modifier
//                            .size(30.dp)
//                            .clip(CircleShape),
//                        contentScale = ContentScale.Crop
//                    )
//                    Spacer(Modifier.width(20.dp))
//                    Box(Modifier.width(80.dp)){
//                        Text(invitation.name)
//                    }
//                    Spacer(Modifier.width(30.dp))
//                    TextButton(
//                        modifier = Modifier
//                            .height(40.dp)
//                            .width(70.dp),
//                        onClick = { viewModel.handleInv(false, invitation.friendId) }) {
//                        Text("Ignore", fontSize = 10.sp)
//                    }
//
//                    Spacer(Modifier.width(5.dp))
//                    OutlinedButton(
//                        modifier = Modifier
//                            .height(30.dp)
//                            .width(90.dp),
//                        onClick = { viewModel.handleInv(true, invitation.friendId) }) {
//                        Text("Accept", fontSize = 10.sp)
//                    }
//                }
//            }
//        }
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

//Statistics
@Composable
fun RowScope.HealthCard(title: String, progress: Float){
    Box(
        modifier = Modifier
            .padding(8.dp)
            .weight(1f)
            .height(160.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title in the top-center
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 16.dp) // Add padding below the title
            )

            // Circular progress bar below the title
            CircularProgressIndicator(
                progress = progress,
                strokeWidth = 10.dp,
                modifier = Modifier.size(80.dp), // Set the size of the circular progress bar
                color = when {
                    progress <= 0.3f -> Color.Red // Red for low progress
                    progress <= 0.6f -> Color.Yellow // Yellow for medium progress
                    else -> Color(0xFF8BC34A) // Green for high progress
                }
            )
        }
    }
}
@Composable
fun CigaretteProgressBox(progress: Float) {
    Box(
        modifier = Modifier
            .padding(top=10.dp, bottom = 10.dp, start=8.dp, end=8.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Cigarettes persisted",
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 8.dp) // Add padding below the title
            )
            Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                LinearProgressIndicator(
                    progress = 0.3f,
                    modifier = Modifier
                        .height(8.dp) // Set the height of the progress bar
                        .fillMaxWidth(0.7f), // Set the width of the progress bar to half of the box
                    color = Color(0xFF8BC34A)
                )
                Text(text="3/10")
            }
            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "Cigarettes smoked",
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 8.dp) // Add padding below the title
            )
            Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                LinearProgressIndicator(
                    progress = 0.7f,
                    modifier = Modifier
                        .height(8.dp) // Set the height of the progress bar
                        .fillMaxWidth(0.7f), // Set the width of the progress bar to half of the box
                    color = Color(0xFFE95247),
                )
                Text(text="7/10")
            }
        }
    }
}

@Composable
fun RowScope.StatisticCard(){
    Box(
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .height(150.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Payments,
                contentDescription = "Icon",
                modifier = Modifier
                    .size(50.dp)
                    .background(color = Color(0xFFFFC658), shape = CircleShape)
                    .padding(10.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "17$",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Money Saved",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}