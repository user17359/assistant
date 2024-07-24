package com.example.assistant.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assistant.R
import com.example.assistant.data.Achievement
import com.example.assistant.data.Tier
import com.example.assistant.data.presets.setOfAchievements

@Composable
fun AchievementBar(achievement: Achievement) {
    ElevatedCard(
        modifier = Modifier.aspectRatio(4f), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(0.35f)
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (achievement.tier != Tier.None)
                    Image(
                        painter = painterResource(
                            id = when (achievement.tier) {
                                Tier.Bronze -> {
                                    R.drawable.medal_3
                                }

                                Tier.Silver -> {
                                    R.drawable.medal_2
                                }

                                Tier.Gold -> {
                                    R.drawable.medal_1
                                }

                                Tier.Diamond -> {
                                    R.drawable.medal_4
                                }

                                else -> {
                                    R.drawable.medal_1
                                }
                            }
                        ), contentDescription = achievement.name, modifier = Modifier.background(
                            MaterialTheme.colorScheme.primary
                        ), contentScale = ContentScale.Crop
                    )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = achievement.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = achievement.description.format(achievement.requiredNumber),
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                LinearProgressIndicator(
                    progress = if (achievement.requiredNumber != 0) achievement.currentNumber / achievement.requiredNumber.toFloat() else 0f,
                    color = Color(0xFF4F75FF),
                    trackColor = MaterialTheme.colorScheme.secondary,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.height(12.dp).fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAchievementBar() {
    AchievementBar(setOfAchievements.first())
}
