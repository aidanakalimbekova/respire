package com.example.smoking.ui.theme.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smoking.network.Challenge
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale


@Composable
fun QuestScreen(viewModel: LineChartViewModel, onClick: () -> Unit, navController: NavController) {
    val state = viewModel.challengeState.collectAsState().value

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
                        onClick = onClick,
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

            when (state) {
                is ChallengeState.Loading -> LoadingView()
                is ChallengeState.Success -> ChallengesListView(state.challengesInv, state.challengesAcc, viewModel, navController)
                is ChallengeState.Error -> ErrorView(state.message)
            }
        }
    }

}
@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ChallengesListView(challengesInv: List<Challenge>, challengesAcc:  List<Challenge>, viewModel: LineChartViewModel, navController: NavController) {
    LazyColumn {
        items(challengesInv) { challenge ->
            InvitedChallengeCard(challenge, viewModel, navController)
        }
        items(challengesAcc) { challenge ->
            AcceptedChallengeCard(challenge, viewModel, navController)
        }
    }
}

@Composable
fun ErrorView(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = Color.Red)
    }
}
@Composable
fun InvitedChallengeCard(challenge: Challenge, viewModel: LineChartViewModel, navController: NavController) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.Black,  //Card content color,e.g.text,
        ),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(Modifier.padding(start = 20.dp, bottom = 20.dp)) {
            Text("Invited by: ${challenge.owner_id}", style = MaterialTheme.typography.bodyMedium)
            Text("challenge.title", style = MaterialTheme.typography.headlineMedium)
            Text("Days left: challenge.daysLeft")
            Button(colors = ButtonDefaults.buttonColors(Color(0XFFFFD366)), onClick = { /* Handle view more */ }) {
                Text("View the quest")
            }
        }
    }
}

@Composable
fun AcceptedChallengeCard(challenge: Challenge, viewModel: LineChartViewModel, navController: NavController) {
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            viewModel.navigateToChallengeDetails(navController, challenge.id)
            // Handle click to view challenge details
        }, colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.Black  //Card content color,e.g.text
        ),
            elevation = CardDefaults.cardElevation()) {
        Column(Modifier.padding(start = 20.dp, bottom = 20.dp)) {
            val originalFormatter = DateTimeFormatter.ISO_DATE_TIME
            val today = LocalDate.now()
            val dateTime = ZonedDateTime.parse(challenge.end_date, originalFormatter)
            val targetDate = dateTime.toLocalDate()
            val daysBetween = ChronoUnit.DAYS.between(today, targetDate).toInt()

            Text(challenge.name, style =  MaterialTheme.typography.headlineMedium)
            Text("Days left: $daysBetween")
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