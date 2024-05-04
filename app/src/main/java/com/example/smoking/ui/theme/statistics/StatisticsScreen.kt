package com.example.smoking.ui.theme.statistics

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.SmokeFree
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smoking.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.checkerframework.common.subtyping.qual.Bottom

@Preview
@Composable
fun StatisticsScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF93F1F7),
                    Color(0xFFF6DF67),
                )
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(){
            StatisticCard()
            StatisticCard()
            StatisticCard()
        }
        CigaretteProgressBox(title="hey", progress=0.7f)
        Text(
            text = "Health Improvements",
            fontSize = 20.sp
        )
        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            Row(){
                HealthCard(title = "Progress", progress = 0.75f)
                HealthCard(title = "Progress", progress = 0.75f)
            }
            Row(){
                HealthCard(title = "Progress", progress = 0.75f)
                HealthCard(title = "Progress", progress = 0.75f)
            }
            Row(){
                HealthCard(title = "Progress", progress = 0.75f)
                HealthCard(title = "Progress", progress = 0.75f)
            }
        }
    }

}
@Composable
fun RowScope.HealthCard(title: String, progress: Float){
    Box(
        modifier = Modifier
            .padding(8.dp)
            .weight(1f)
            .height(160.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp), // Add padding inside the box
        contentAlignment = Alignment.Center
    ) {
        // Column to arrange the title and circular progress bar vertically
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title in the top-center
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 16.dp) // Add padding below the title
            )

            // Circular progress bar below the title
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(60.dp), // Set the size of the circular progress bar
                color = when {
                    progress <= 0.3f -> Color.Red // Red for low progress
                    progress <= 0.6f -> Color.Yellow // Yellow for medium progress
                    else -> Color.Green // Green for high progress
                }
            )
        }
    }
}
@Composable
fun CigaretteProgressBox(title: String, progress: Float) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(130.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 8.dp) // Add padding below the title
            )

            Text(
                text = "Cigarettes persisted",
                modifier = Modifier.padding(bottom = 8.dp) // Add padding below the title
            )
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .height(8.dp) // Set the height of the progress bar
                    .fillMaxWidth(0.5f), // Set the width of the progress bar to half of the box
                color = when {
                    progress <= 0.3f -> Color.Red // Red for low progress
                    progress <= 0.6f -> Color.Yellow // Yellow for medium progress
                    else -> Color.Green // Green for high progress
                }
            )
            Text(
                text = "Cigarettes smoked",
                modifier = Modifier.padding(bottom = 8.dp) // Add padding below the title
            )
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .height(8.dp) // Set the height of the progress bar
                    .fillMaxWidth(0.5f), // Set the width of the progress bar to half of the box
                color = when {
                    progress <= 0.3f -> Color.Red // Red for low progress
                    progress <= 0.6f -> Color.Yellow // Yellow for medium progress
                    else -> Color.Green // Green for high progress
                }
            )
        }
    }
}

@Composable
fun RowScope.StatisticCard(){
    Box(
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .height(150.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star Icon",
                modifier = Modifier.size(40.dp),
                tint = Color.Blue
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "17",
                fontSize = 20.sp
            )
            Text(
                text = "Money Saved",
                fontSize = 10.sp
            )
        }
    }
}
