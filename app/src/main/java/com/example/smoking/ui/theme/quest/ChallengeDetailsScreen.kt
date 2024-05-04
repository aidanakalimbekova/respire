package com.example.smoking.ui.theme.quest

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.smoking.network.ChallengeX
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun ChallengeDetailsScreen( navController: NavController, viewModel: ChallengeDetailViewModel){

    val challengeState by viewModel.challengeState.collectAsStateWithLifecycle()

    Column {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top) {
            Button(
                onClick = { navController.navigate("quest") },
                shape = CircleShape,
                modifier = Modifier.size(40.dp),
                contentPadding = PaddingValues(1.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Close",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        }

        when (val state = challengeState)  {
            is ChallengesState.Loading -> CircularProgressIndicator()
            is ChallengesState.Success -> {
                ChallengeDetailContent(state.challenge)
            }
            is ChallengesState.Error -> Text("Error: ${state.message}")
        }

    }


}

@Composable
fun ChallengeDetailContent(challenge: ChallengeX?){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.padding(20.dp)) {

            if (challenge != null) {
                Text(
                    challenge.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }
        }
    }

    Box(modifier = Modifier.padding(20.dp)) {
        if (challenge != null) {
            println(challenge.end_date)
            val originalFormatter = DateTimeFormatter.ISO_DATE_TIME
             val dateTime = ZonedDateTime.parse(challenge.end_date, originalFormatter)
            val newFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd", Locale.ENGLISH)
            val formattedDate = dateTime.format(newFormatter)
            Text(
                formattedDate,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            )
        }
    }

    Box(modifier = Modifier.padding(20.dp)) {
        if (challenge != null) {
            Text(
                challenge.description,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start
            )
        }
    }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                verticalArrangement = Arrangement.Center,
            ){
                if (challenge != null) {
                    itemsIndexed(challenge.leaderboard){ index, friend->
                        Row(
                            modifier = Modifier.padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                            Column {
                                Row {
                                    Box(){
                                        Text("${index + 1}", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                                    }
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Box(){
                                        Text(friend.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                                    }
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Row (verticalAlignment = Alignment.CenterVertically){
                                   AsyncImage(
                                        model = friend.avatar,
                                        contentDescription = "Profile picture",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Box(modifier = Modifier.width(240.dp)){
//                                        if(friend.smoked == 0){
//                                            Box(
//                                                modifier = Modifier
//                                                    .background(Color(0xFFF9F9F9))
//                                                    .width(240.dp)
//                                            ){
//                                                Text(text = "Rockstar!", fontSize = 15.sp, color = Color.Black)
//                                            }
//                                        }else{
//                                            CustomProgressBar(
//                                                Modifier
//                                                    .clip(shape = RoundedCornerShape(30.dp))
//                                                    .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D), s = friend.smoked, true )
//                                        }
                                    }
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Box(){
//                                        Text("${friend.smoked}", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                                    }
                                }
                            }
                        }
                    }
                }
        }



}