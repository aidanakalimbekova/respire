package com.example.smoking.ui.theme.quest

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import com.example.smoking.network.Challenge


@Composable
fun QuestScreen(viewModel: LineChartViewModel) {

    Column(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.padding(20.dp)) {
                Text(
                    "Friend's Quest",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }
            Box(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = {  },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "add",
                            tint = Color.Black
                        )
                        Text("Create new quest")
                    }

            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.challenges.value.filter { it.status == ChallengeStatus.INVITED }) { challenge ->
                    InvitedChallengeCard(challenge)
                }
                items(viewModel.challenges.value.filter { it.status == ChallengeStatus.ACCEPTED }) { challenge ->
                    AcceptedChallengeCard(challenge)
                }
            }
        }
    }

}

@Composable
fun InvitedChallengeCard(challenge: Challenge1) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.Black,  //Card content color,e.g.text,
        ),
        elevation = CardDefaults.cardElevation()
    ) {
        Column {
            Text("Invited by: ${challenge.invitedBy}", style = MaterialTheme.typography.bodySmall)
            Text(challenge.title, style = MaterialTheme.typography.headlineMedium)
            Text("Days left: ${challenge.daysLeft}")
            Button(colors = ButtonDefaults.buttonColors(Color(0XFFFFD366)), onClick = { /* Handle view more */ }) {
                Text("View the quest")
            }
        }
    }
}

@Composable
fun AcceptedChallengeCard(challenge: Challenge1) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            // Handle click to view challenge details
        }, colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.Black  //Card content color,e.g.text
        ),
            elevation = CardDefaults.cardElevation()) {
        Column {
            Text("buy me coffee", style =  MaterialTheme.typography.headlineLarge)
            Text("Days left: ${6}")
        }
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