package com.example.smoking.ui.theme.profile

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun InvitationsScreen(onClick: () -> Unit, viewModel: InvitationsViewModel) {
    val profiles = viewModel.invitationProfiles.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value


    Column {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top) {
            Button(
                onClick = onClick,
                shape = CircleShape,
                modifier = Modifier.size(40.dp),
                contentPadding = PaddingValues(1.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            ) {
                // Inner content including an icon and a text label
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Close",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        }

        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier.padding(20.dp)){
                Text("List of Invitations", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
            }
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else if (errorMessage.isNotEmpty()) {
            Text("Error: $errorMessage")
        } else {
            LazyColumn {
                items(profiles){ profile->
                    FriendItem(profile, viewModel::handleInvitation)
                }
            }
        }
    }
}

@Composable
fun FriendItem(profile: InvitationProfile, onHandleInvitation: (Boolean, String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent, //Card background color
            contentColor = Color.Black  //Card content color,e.g.text
        )) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = profile.avatar,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp).weight(1f)) {
                Text(text = profile.name)
                Text(text = "@${profile.username}")
            }
            Button(onClick = { onHandleInvitation(true, profile.invitationId) }, modifier = Modifier.padding(4.dp)) {
                Text("Accept")
            }
            Button(onClick = { onHandleInvitation(false, profile.invitationId) }, modifier = Modifier.padding(4.dp)) {
                Text("Deny")
            }
        }
    }
}