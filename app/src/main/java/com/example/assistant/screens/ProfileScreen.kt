package com.example.assistant.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assistant.R
import com.example.assistant.elements.AchievementBar
import com.example.assistant.elements.BottomNavBar
import com.example.assistant.viewModel.AchievementViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, achievementViewModel: AchievementViewModel) {

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                achievementViewModel.getAchievementList()
            }
        }
    }

    val exercisePlan = achievementViewModel.achievementList.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Profil")
                }
            )
        },
        bottomBar = { BottomNavBar(selected = 4, navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Witaj Kasia!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
                )
                Box(
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.awatar1),
                        contentDescription = null,
                        modifier = Modifier
                            .size(240.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Black, CircleShape)
                    )
                    IconButton(
                        modifier = Modifier
                            .offset((-6).dp, (-6).dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(2.dp, Color.Black, CircleShape),
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_create_24),
                            contentDescription = null
                        )
                    }
                }
                Divider(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 8.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                )
                Text(
                    text = "Twoje osiągniecia",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp)
                ) {
                    items(exercisePlan.value?.toList() ?: listOf()) { achievement ->
                        AchievementBar(achievement = achievement)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController(), viewModel())
}