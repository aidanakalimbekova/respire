package com.example.smoking.ui.theme.quest

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import coil.compose.AsyncImage
import com.example.smoking.R


@Composable
fun QuestScreen(viewModel: LineChartViewModel){
    val s = viewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier.background(Color(0XFFCDEFF3)), verticalArrangement = Arrangement.Center){
            Box(modifier = Modifier.padding(20.dp)){
                Text("Friend's Quest", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
            }
            Column (modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Box(){
                    Image(
                        contentDescription = "third", painter = painterResource(id = R.drawable.cloud),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Column (modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                        Spacer(Modifier.height(30.dp))
                        Box(){
                            Text("Buy me coffee", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Box(Modifier.padding(top = 10.dp)){
                            Text("20-27 Feb", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

//        LazyColumn (modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 30.dp, end = 30.dp), verticalArrangement = Arrangement.Center,
//        ){
//            itemsIndexed(s.value){ index, friend->
//                Row(
//                    modifier = Modifier.padding(top = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
//                    Column {
//                        Row {
//                            Box(){
//                                Text("${index + 1}", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
//                            }
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Box(){
//                                Text(friend.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(5.dp))
//                        Row (verticalAlignment = Alignment.CenterVertically){
//                            val c : Uri = Uri.parse(friend.photo)
//                            AsyncImage(
//                                model = c,
//                                contentDescription = "Profile picture",
//                                modifier = Modifier
//                                    .size(30.dp)
//                                    .clip(CircleShape),
//                                contentScale = ContentScale.Crop
//                            )
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Box(modifier = Modifier.width(240.dp)){
//                                if(friend.smoked == 0){
//                                    Box(
//                                        modifier = Modifier
//                                            .background(Color(0xFFF9F9F9))
//                                            .width(240.dp)
//                                    ){
//                                        Text(text = "Rockstar!", fontSize = 15.sp, color = Color.Black)
//                                    }
//                                }else{
//                                    CustomProgressBar(
//                                        Modifier
//                                            .clip(shape = RoundedCornerShape(30.dp))
//                                            .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
//                                        , s = friend.smoked, true )
//                                }
//                            }
//                            Spacer(modifier = Modifier.width(5.dp))
//                            Box(){
//                                Text("${friend.smoked}", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
//                            }
//                        }
//                    }
//                }
//            }
//        }

    }
}
@Composable
fun CustomProgressBar(
    modifier: Modifier, width: Dp, backgroundColor: Color, foregroundColor: Color, s: Int,
    isShownText: Boolean
) {
    val percent = (s.toFloat() / 15) * 100
    Box(
        modifier = modifier
            .background(foregroundColor)
            .width((width * percent / 100))
    )
}