package com.example.smoking.ui.theme.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.network.HttpException
import com.example.smoking.R
import com.example.smoking.ui.theme.home.Calendar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileScreen(viewModel: ProfileViewModel){
//    val systemUiController = rememberSystemUiController()
//    SideEffect {
//        systemUiController.setStatusBarColor(
//            color = Color(0XFFCDEFF3)
//        )
//    }
//    viewModel.listOfInvitations()
//    val state by remember { viewModel.state }
    Column(){
//        var name:String? = "j"
//        curUser?.run {
//            name = curUser.displayName
//        }
        val auth = Firebase.auth
        val curUser = auth.currentUser
        var pic: Uri? = Uri.EMPTY
        var name:String? = ""
        var uid:String? = ""
        curUser?.run {
            pic = curUser.photoUrl
            name = curUser.displayName
            uid = curUser.uid
        }

        Column (horizontalAlignment = Alignment.Start){
            Column (Modifier.background(Color(0XFFCDEFF3))){
                Box(Modifier.padding(20.dp)){
                    Text("Profile", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                }
                Box(){
                    Image(
                        contentDescription = "third", painter = painterResource(id = R.drawable.third),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(20.dp)){
                        Text("$name", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                        Spacer(Modifier.width(100.dp))
                        AsyncImage(
                            model = pic,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Column (Modifier.padding(20.dp)){
                var text by remember { mutableStateOf("") }
                val clipboardManager = LocalClipboardManager.current
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Box{
                        Text("Share your id with friends", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(Modifier.width(20.dp))
                    Button(onClick = { clipboardManager.setText(AnnotatedString("$uid")) }) {
                        Icon(imageVector = Icons.Outlined.FileCopy, contentDescription = "clip")
                    }
                }
                Box{
                    Text("$uid", fontSize = 10.sp, textAlign = TextAlign.Start)
                }
                Spacer(Modifier.height(20.dp))
                Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Invite your friend") },
                        modifier = Modifier.width(200.dp)
                    )
                    Spacer(Modifier.width(30.dp))
                    Box{
                        Button(onClick = {
//                        println(text)
                            viewModel.invite(text)
                            text = ""
                        }) {
                            Text("Invite")
                        }
                    }
                }
                Box(Modifier.padding(top = 20.dp)){
                    Text("Invitations", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                }
                val s by viewModel.state.collectAsState()
                LazyColumn(){
                    items(s, key = { it.friendId }) { invitation ->
                        Box(){
                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                                AsyncImage(
                                    model = invitation.photoUrl,
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(Modifier.width(20.dp))
                                Box(Modifier.width(80.dp)){
                                    Text(invitation.name)
                                }
                                Spacer(Modifier.width(30.dp))
                                TextButton(
                                    modifier = Modifier
                                        .height(40.dp)
                                        .width(70.dp),
                                    onClick = { viewModel.handleInv(false, invitation.friendId) }) {
                                    Text("Ignore", fontSize = 10.sp)
                                }

                                Spacer(Modifier.width(5.dp))
                                OutlinedButton(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(90.dp),
                                    onClick = { viewModel.handleInv(true, invitation.friendId) }) {
                                    Text("Accept", fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }

        }

    }

}
