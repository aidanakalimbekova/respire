package com.example.smoking.ui.theme.home

import androidx.compose.ui.res.vectorResource
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(viewModel:HomeViewModel) {

    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("E, d MMM"))
//    val auth = Firebase.auth
//    val curUser = auth.currentUser
        Column(){
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Column (Modifier.background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF93F1F7),
                        Color(0xFFF6DF67),
                    )
                )), horizontalAlignment = Alignment.CenterHorizontally){
                    //hey, rimma
                    Spacer(modifier = Modifier.padding(top=15.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        ) {
                        Row(){
                            Text("Hey, ", fontSize = 25.sp, textAlign = TextAlign.Start)
                            Text("Rimma!", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                        }
                        Row(
                            modifier = Modifier
                                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                                .border(
                                    border = BorderStroke(1.dp, Color.White),
                                    shape = RoundedCornerShape(200.dp)
                                )
                                .padding(top = 5.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                            ){
                            Text(formattedDate, fontSize = 15.sp, textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                        }
                    }
                    //shetchik
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Text("YOU HAVEN’T SMOKED FOR", fontSize = 15.sp, textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                        Text("1 Day", fontSize = 60.sp, textAlign = TextAlign.Start, fontWeight = FontWeight.ExtraBold)
                        Text("1d:08h:48m:32s", fontSize = 15.sp, textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.plant),
                        contentDescription = "",
                        modifier = Modifier
                            .size(200.dp, 200.dp) // Set the image size
                            .padding(16.dp), // Optional padding around the image
                        alignment = Alignment.Center // Optional alignment
                    )

                    DashboardCard(viewModel)
                }
//                Calendar(viewModel)

//                PersonalInfo()
            }

        }
    }

@Composable
fun DashboardCard(viewModel:HomeViewModel){
    val progress = 0.1f
    var showBottomSheet = remember {
        mutableStateOf(false)
    }

    Box (modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .height(300.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Text(
                    text = "Tracker",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Column(modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()){
                Text(
                    text = "You have smoked 1 cigarette(s) out of 10",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(10.dp))

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .height(10.dp)
                        .align(Alignment.CenterHorizontally),
                    color = when {
                        progress <= 0.3f -> Color.Red // Red for low progress
                        progress <= 0.6f -> Color.Yellow // Yellow for medium progress
                        else -> Color.Green // Green for high progress
                    }
                )
            }

        }
        FloatingActionButton(
            onClick = {showBottomSheet.value = true},
            containerColor = Color(0xFF93F1F7), // Set the FAB background color
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(70.dp),
        ){
            Icon(
                imageVector = Icons.Default.Add, // Use any appropriate icon
                contentDescription = "Add",
                tint = Color.Black // Set the icon color to white for contrast
            )
        }

        if(showBottomSheet.value){
            BottomSheet(viewModel)
        }
    }
}

//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(viewModel:HomeViewModel) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val modalBottomSheet = rememberModalBottomSheetState()
    ModalBottomSheet(
        modifier = Modifier.height(600.dp),
        onDismissRequest = { /*TODO*/ },
        sheetState = modalBottomSheet,
        dragHandle = {BottomSheetDefaults.DragHandle()},
        content = {
            Column(modifier = Modifier
                .padding(start = 25.dp, end = 25.dp)
                .fillMaxWidth()) {
                Text(text = "I've smoked", fontSize = 25.sp, fontWeight = FontWeight.Bold)

                // counter
                Box(){}

                CounterCard(viewModel)

                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "How strong was your craving?", fontSize=20.sp)

                Column(modifier=Modifier.fillMaxWidth()) {
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        steps = 3,
                        valueRange = 0f..50f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFF41999E),
                        ),
                    )

                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = "How did you feel?", fontSize=20.sp, modifier = Modifier.padding(bottom=5.dp))

                    var text by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(modifier = Modifier.fillMaxWidth(),value = text, onValueChange = {
                            newText -> text = newText
                    })
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color(0xFF041725),       // цвет текста
                            containerColor = Color(0xFF93F1F7))) {
                        Text(text = "Submit", fontSize=20.sp, modifier = Modifier.padding(start=5.dp, end=5.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun CounterCard(viewModel:HomeViewModel){
    Box (modifier = Modifier.padding(10.dp))
    {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            ){
//                viewModel.retrieveCounterFromFirestore()
                var valueCounter by remember {
                    mutableIntStateOf(0)
                }
                CounterButton(value = valueCounter.toString(),
                    onValueIncreaseClick = {
                        valueCounter += 1
                        println(valueCounter)
//                        viewModel.updateCounterForSelectedDay(valueCounter.plus(1) ?: 0)
                    },
                    onValueDecreaseClick = {
                        valueCounter = maxOf(valueCounter - 1, 0)
//                        viewModel.updateCounterForSelectedDay(maxOf(valueCounter.minus(1) ?: 0, 0))
                    },
                    onValueClearClick = {
                        valueCounter = 0
                    })
            }
    }
}


