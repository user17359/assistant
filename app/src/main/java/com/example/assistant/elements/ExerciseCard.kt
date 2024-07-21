package com.example.assistant.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assistant.R
import com.example.assistant.data.Exercise

@Composable
fun ExerciseCard(exercise: Exercise) {
    ElevatedCard(
        modifier = Modifier.aspectRatio(0.8f).
        clickable {

        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
    {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = exercise.image),
                contentDescription = exercise.name,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                contentScale = ContentScale.Crop
            )
            if(exercise.status) {
                Image(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Zrobione"
                )
            }
        }
        Column(
            modifier = Modifier.weight(0.45f)
        ) {
            Text(
                text = exercise.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            Text(
                text = exercise.durationSeconds.toString() + " sekund",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun ExerciseCardPreview() {
    ExerciseCard(Exercise(R.drawable.brzuszki, "Brzuszki", 30, true))
}