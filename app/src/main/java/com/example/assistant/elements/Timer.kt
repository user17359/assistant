package com.example.assistant.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Timer(modifier: Modifier, maxTime: Int, currentTime: Float?){

    val strokeWidth = 20.dp
    val background = MaterialTheme.colorScheme.primaryContainer
    val progress = MaterialTheme.colorScheme.primary

    Box (
        modifier = Modifier.aspectRatio(1f),
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier) {
            drawArc(
                color = background,
                startAngle = -180f,
                sweepAngle = 360f,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = progress,
                startAngle = -180f,
                sweepAngle = 360f * (currentTime ?: maxTime).toFloat() / maxTime,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        if(currentTime != null)
            Text(text = "%.1f".format(currentTime), fontSize = 70.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    }
}

@Preview
@Composable
fun TimerPreview(){
    Timer(
        Modifier
            .fillMaxSize()
            .padding(10.dp), 30, 25f)
}