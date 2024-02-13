package com.example.smoking.ui.theme.home

import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

//@Preview
@Composable
fun HomeScreen(){

    Column(Modifier.padding(20.dp)){

        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth())
        {
            Text("Hey, ", fontSize = 25.sp, textAlign = TextAlign.Start)
            Text("Rimma!", fontSize = 25.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom,){
                Icon(Icons.Filled.LocalFireDepartment, contentDescription =  "strike", tint = Color(0xFFFF5722) )
                Text("5", fontSize = 20.sp, modifier = Modifier.padding(start = 4.dp))
            }
        }
        Card {
            Column {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Text",
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "5 days",
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    var valueCounter by remember {
                        mutableIntStateOf(0)
                    }
                CounterButton(value = valueCounter.toString(),
                    onValueIncreaseClick = {
                        valueCounter += 1
                    },
                    onValueDecreaseClick = {
                        valueCounter = maxOf(valueCounter - 1, 0)
                    },
                    onValueClearClick = {
                        valueCounter = 0
                    })
                }
            }
        }

    }

}


@Composable
private fun CounterButton(
    value: String,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(200.dp).height(80.dp)
    ) {
        ButtonContainer(
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            onValueClearClick = onValueClearClick,
            modifier = Modifier
        )
        DraggableThumbButton(
            value = value,
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
private const val ICON_BUTTON_ALPHA_INITIAL = 0.3f
private const val CONTAINER_BACKGROUND_ALPHA_INITIAL = 0.6f

@Composable
private fun ButtonContainer(
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    onValueClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    clearButtonVisible: Boolean = false,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(64.dp))
            .background(Color.Black.copy(alpha = CONTAINER_BACKGROUND_ALPHA_INITIAL))
            .padding(horizontal = 8.dp)
    ) {
        // decrease button
        IconControlButton(
            icon = Icons.Outlined.Remove,
            contentDescription = "Decrease count",
            onClick = onValueDecreaseClick,
            tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
        )

        // clear button
        if (clearButtonVisible) {
            IconControlButton(
                icon = Icons.Outlined.Clear,
                contentDescription = "Clear count",
                onClick = onValueClearClick,
                tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
            )
        }

        // increase button
        IconControlButton(
            icon = Icons.Outlined.Add,
            contentDescription = "Increase count",
            onClick = onValueIncreaseClick,
            tintColor = Color.White.copy(alpha = ICON_BUTTON_ALPHA_INITIAL)
        )
    }
}

@Composable
private fun IconControlButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tintColor: Color = Color.White,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(48.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tintColor,
            modifier = Modifier.size(32.dp)
        )
    }
}


@Composable
private fun DraggableThumbButton(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(8.dp, shape = CircleShape)
            .size(64.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(Color.Gray)
    ) {
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
        )
    }
}