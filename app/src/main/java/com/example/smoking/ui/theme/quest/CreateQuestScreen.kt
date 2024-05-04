package com.example.smoking.ui.theme.quest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateQuestScreen(viewModel: CreateChallengeViewModel, onClick: () -> Unit){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var prize by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var penalty by remember { mutableStateOf("") }
    var cigarettesLimit by remember { mutableStateOf("") }
    var invited = remember { mutableStateListOf<String>() }

    Column {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top) {
            Button(
                onClick = onClick,
                shape = CircleShape,
                modifier = Modifier.size(40.dp),
                contentPadding = PaddingValues(1.dp),
                colors = ButtonDefaults.outlinedButtonColors(Color.Transparent)
            ) {
                // Inner content including an icon and a text label
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Close",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding(20.dp)){
                Text("Create a quest", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
            }
        }





    }

}