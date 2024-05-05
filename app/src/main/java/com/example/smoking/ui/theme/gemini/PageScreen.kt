package com.example.smoking.ui.theme.gemini

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smoking.R

@Composable
fun PageScreen(navController: NavHostController, pageViewModel: PageViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray
                ),
                shape = RectangleShape,
            ) {
//                Text("X", fontsize=10.sp)
                Text("close", fontSize = 15.sp)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            Text("Your daily recommendations", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            val recommendations = listOf(
                "Take a short walk outside the office to change the scenery and get some fresh air.",
                "Instead of smoking, try sipping on a glass of water or chewing on a piece of gum to keep your mouth busy.",
                "Engage in a quick office-friendly activity like solving a puzzle or reading a short article to keep your mind occupied.",
                "To improve your mood, listen to your favorite music or a podcast that you enjoy while working.",
                "Consider setting a mini goal for the day at work to keep yourself motivated and focused, which can also help distract from the craving."
            )
            LazyColumn{
                items(recommendations){
                        recommendation ->
                    Text(
                        text = recommendation,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 25.sp, fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.safeContentPadding())
                }
            }
     }
    }
}
