package com.example.smoking.ui.theme.quest

import android.net.Uri
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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

@Preview(showBackground = true)
@Composable
fun QuestScreen(){
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFFF9F9F9)
        )
    }
    val auth = Firebase.auth
    val curUser = auth.currentUser
    var pic: Uri? = Uri.EMPTY
    curUser?.run {
        pic = curUser.photoUrl
    }
    var componentWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Column (modifier = Modifier.fillMaxSize().padding(20.dp)){

        Box(modifier = Modifier){
            Text("Friend's Quest", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
        }

        LazyColumn (modifier = Modifier.fillMaxWidth().padding(10.dp), verticalArrangement = Arrangement.Center,
            ){
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
            item{
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Box(){
                        Text("1", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    AsyncImage(
                        model = pic,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CustomProgressBar(Modifier
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D)
                        , percent = 100, true )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(){
                        Text("12", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                    }
                }
            }
        }


    }


}
@Composable
fun CustomProgressBar(
    modifier: Modifier, width: Dp, backgroundColor: Color, foregroundColor: Color, percent: Int,
    isShownText: Boolean
) {
    Box(
        modifier = modifier
            .background(foregroundColor)
            .width(width * percent / 100)
    )
}