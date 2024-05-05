package com.example.smoking.ui.theme.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.smoking.network.UserX
import com.google.firebase.firestore.auth.User

//@Preview
@Composable
fun AddFriendScreen(onClick: () -> Unit, viewModel: AddFriendViewModel) {
    val message = remember{mutableStateOf("")}
    val users = viewModel.users.value
    val isLoading = viewModel.isLoading.value
    val invitationStatus = viewModel.invitationStatus.value

    Column(modifier = Modifier.padding(10.dp)) {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top){
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

        Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Text(text="Invite a friend", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.padding(10.dp))

        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = message.value,
                textStyle = TextStyle(fontSize=20.sp),
                onValueChange = {newText -> message.value = newText}
            )
            Button(
                onClick = { viewModel.searchUsers(message.value) },
                shape = CircleShape,
                modifier = Modifier.size(30.dp),
                contentPadding = PaddingValues(1.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            ) {
                // Inner content including an icon and a text label
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Close",
                    modifier = Modifier.size(25.dp),
                    tint = Color.Black
                )
            }
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(users){ user->

                    UserItem(user = user, onInviteClick = { viewModel.sendInvitation(user.id) }, isInvited = invitationStatus[user.id] == true )
                }
            }
        }

    }
}

@Composable
fun UserItem(user: UserX, onInviteClick: () -> Unit,  isInvited: Boolean){
    Box(){
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(start=10.dp, end=10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){

            Row(){
                AsyncImage(
                    model = user.avatar,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(30.dp).padding(end=5.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(text=user.name, fontSize = 20.sp)
            }

            Spacer(Modifier.width(20.dp))

            if (isInvited) {
                Text("Sent", color = MaterialTheme.colorScheme.secondary)
            } else {
                TextButton(
                    onClick = onInviteClick) {
                    Text("Send Invite", fontSize = 20.sp)
                }
            }

        }
    }
}

