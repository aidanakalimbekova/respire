package com.example.smoking.ui.theme.statistics

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.SmokeFree
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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


@Composable
fun StatisticsScreen(){
    Image(contentDescription = "cloud", painter = painterResource(id = R.drawable.cloud),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth())

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(20.dp)){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 20.dp))
            {
                Text(text = "I HAVEN'T SMOKED FOR", fontSize = 15.sp)
                Text(text = "5 DAYS", fontSize = 50.sp)
                Text(text = "5d:16h:38m:33s", fontSize = 15.sp)
            }
        }
        Row(modifier = Modifier.padding(15.dp)){
            Column(){
                StatisticCard(text="12 cigs", subText="CONSUMED IN A YEAR", height=80.dp, icon=Icons.Filled.Leaderboard)
                Spacer(modifier = Modifier.height(10.dp))
                StatisticCard(text = "2200", subText = "MONEY SAVED", height =80.dp , icon =Icons.Filled.Payments )
            }
            Spacer(modifier = Modifier.width(10.dp))
            StatisticCard(text = "12 days", subText = "SMOKE-FREE IN A YEAR", height =170.dp , icon =Icons.Filled.SmokeFree)
        }
    }

}
@Composable
fun StatisticCard(text: String,
                  subText:String,
                  height: Dp,
                  icon: ImageVector,
){
    Box(modifier = Modifier
        .height(height = height)
        .width(170.dp)
        .background(color = Color(0xfff2f4f7), shape = RoundedCornerShape(8.dp))
        .border(width = 1.dp, color = Color(0xffa3be94), shape = RoundedCornerShape(8.dp))
        .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Icon(icon, contentDescription = "statistics", tint = Color(0xff9dd67d))
        }
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
            Text(text = text, fontSize=18.sp, fontWeight = FontWeight.Bold)
            Text(text = subText, fontSize = 12.sp)
        }
    }
}