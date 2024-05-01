package com.example.smoking.ui.theme.home

import androidx.compose.ui.res.vectorResource
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smoking.R
import com.example.smoking.ui.theme.signin.GoogleAuthUiClient
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.time.LocalDate

//@Preview
@Composable
fun HomeScreen(viewModel:HomeViewModel){
    val auth = Firebase.auth
    val curUser = auth.currentUser

        Column(){
            var name:String? = "j"
            curUser?.run {
                name = curUser.displayName
            }
            viewModel.getStreak()
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Column (Modifier.background(Color(0XFFCDEFF3))){
                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(20.dp), )
                    {
                        Text("Hey, ", fontSize = 25.sp, textAlign = TextAlign.Start)
                        Text("$name", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom,){
                            Icon(Icons.Filled.LocalFireDepartment, contentDescription =  "strike", tint = Color(0xFFFF5722) )
                            Text(viewModel.selectedDay.value.streak, fontSize = 20.sp, modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }
                Calendar(viewModel)

//                PersonalInfo()
            }

        }
    }

@Composable
fun PersonalInfo(){
    Column (modifier = Modifier
        .padding(20.dp)){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ){
            Text(
                text = "Personalised for you",
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                color = Color.Black
            )
        }
        Column ( ) {
            Row (modifier = Modifier
                .fillMaxWidth()){
                Card(modifier = Modifier.size(150.dp)){
                    Box(
                        modifier = Modifier
                    ){
                        Text(
                            text = "Personalised for you",
                            textAlign = TextAlign.Start,
                            fontSize = 10.sp,
                            color = Color.Black
                        )
                    }
                }
                Card(modifier = Modifier.size(150.dp)){
                    Box(
                        modifier = Modifier
                    ){
                        Text(
                            text = "Personalised for you",
                            textAlign = TextAlign.Start,
                            fontSize = 10.sp,
                            color = Color.Black
                        )
                    }
                }
            }

        }

    }
}
@Composable
fun Calendar(viewModel:HomeViewModel){

    Column{
        Column (){
            Box(){
                Image(
                    contentDescription = "sun", painter = painterResource(id = R.drawable.sun),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth(),
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (verticalAlignment = Alignment.CenterVertically){
//                        Button(onClick = onSelectYesterday,
//                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2F4F7),
//                                contentColor = Color(0xFF072100)
//                            ),
//                            modifier = Modifier.size(width = 50.dp, height = 50.dp)
////               shape = RoundedCornerShape(40.dp),
//
//                        ) {
//                            Text("<")
//                        }
//
//                        Button(onClick = onSelectTomorrow,
//                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2F4F7),
//                                contentColor = Color(0xFF072100)
//                            ),
////               shape = RoundedCornerShape(40.dp),
//                            modifier = Modifier.size(width = 50.dp, height = 50.dp)
//                        ) {
//                            Text(">")
//                        }
                        Text(
                            text = viewModel.selectedDay.value.label,
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        )
                    }
                    Column {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = viewModel.selectedDay.value.day,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
//                            modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = viewModel.selectedDay.value.dayOfWeek,
                                textAlign = TextAlign.Center,
                                color = Color(0xFF79747E),
                                fontSize = 15.sp,
                                modifier = Modifier.padding(top = 10.dp, bottom = 25.dp)
                            )
                        }
                    }
                }
            }
        }
        Column(
            Modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(60.dp))){

            CounterCard(viewModel)
        }

    }
}
@Composable
fun CounterCard(viewModel:HomeViewModel){
    Box (Modifier.background(Color(0xFF9DD67D)))
    {

        Column() {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ){
                Text(
                    text = "I smoked today",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ){

                Text(
                    text = viewModel.selectedDay.value.counter.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF9DD67D))
                    .padding(bottom = 30.dp)
            ){
//                var valueCounter by remember {
//                    mutableIntStateOf(0)
//                }
                viewModel.retrieveCounterFromFirestore()
                var valueCounter = viewModel.selectedDay.value.counter as? Long
                CounterButton(value =viewModel.selectedDay.value.counter.toString(),
                    onValueIncreaseClick = {
                        viewModel.updateCounterForSelectedDay(valueCounter?.plus(1) ?: 0)
                    },
                    onValueDecreaseClick = {
                        viewModel.updateCounterForSelectedDay(maxOf(valueCounter?.minus(1) ?: 0, 0))
                    },
                    onValueClearClick = {
                        valueCounter = 0
                    })
            }
        }
    }
}


