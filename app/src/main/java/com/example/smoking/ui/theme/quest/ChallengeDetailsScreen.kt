package com.example.smoking.ui.theme.quest

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Brush
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

    Column(modifier = Modifier.background(brush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF93F1F7),
            Color(0xFFF6DF67),
        )
    ))) {
        Row(modifier = Modifier.padding(20.dp),horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top) {
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
            is ChallengesState.Loading -> {
                Row(modifier=Modifier.fillMaxSize(),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    CircularProgressIndicator()
                }
            }
            is ChallengesState.Success -> {
                ChallengeDetailContent(state.challenge)
            }
            is ChallengesState.Error -> Text("Error: ${state.message}")
        }

    }


}

@Composable
fun ChallengeDetailContent(challenge: ChallengeX?){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box() {
            if (challenge != null) {
                Text(
                    challenge.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }
        }
        Box() {
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
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        if (challenge != null) {
            Text(
                text=challenge.description,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
    }

    Spacer(modifier = Modifier.padding(5.dp))

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(
            color = Color.White,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        )
        .padding(top = 15.dp), verticalArrangement = Arrangement.Center) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            text = "LeaderBoard",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
                .padding(start = 30.dp,end=30.dp, top = 15.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            if (challenge != null) {
                itemsIndexed(challenge.leaderboard) { index, friend ->
                    Row(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    Text(
                                        "${index + 1}",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Start
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        friend.name,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                AsyncImage(
                                    model = friend.avatar,
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            //green thing
                            Box(modifier = Modifier.width(240.dp)){
                                        if(friend.smoke_count == 0){
                                        }else{
                                            CustomProgressBar(
                                                Modifier
                                                    .clip(shape = RoundedCornerShape(30.dp))
                                                    .height(14.dp), 240.dp, Color.Gray, Color(0xFF9DD67D), s = friend.smoke_count, true )
                                        }
                            }
                            Box(){
                                        Text("${friend.smoke_count}", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                            }
                        }
                    }
                }
            }
        }
    }
}