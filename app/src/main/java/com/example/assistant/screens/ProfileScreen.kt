package com.example.assistant.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assistant.data.presets.setOfAchievements
import com.example.assistant.elements.AchievementBar
import com.example.assistant.elements.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){
    Scaffold (
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
    ){ innerPadding ->
        Box (
            modifier = Modifier.padding(innerPadding)
        ){
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 12.dp)
            ) {
                items(setOfAchievements.toList()){ achievement ->
                    AchievementBar(achievement = achievement)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(rememberNavController())
}