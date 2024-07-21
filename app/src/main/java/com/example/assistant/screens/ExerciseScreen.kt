package com.example.assistant.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.assistant.elements.BottomNavBar

@Composable
fun ExerciseScreen(navController: NavController) {
    Scaffold (
        bottomBar = { BottomNavBar(selected = 3, navController = navController) }
    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ){

        }
    }
}