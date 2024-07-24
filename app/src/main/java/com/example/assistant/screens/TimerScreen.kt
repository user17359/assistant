package com.example.assistant.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assistant.AssistantScreen
import com.example.assistant.elements.Timer
import com.example.assistant.viewModel.ExerciseViewModel
import kotlinx.coroutines.launch

@Composable
fun TimeScreen(navController: NavController, viewModel: ExerciseViewModel, exerciseID: Int){

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true){
        coroutineScope.launch {
            viewModel.startupTimer(exerciseID)
        }
    }

    val finishedExercise by viewModel.finishedExercise.collectAsState()

    LaunchedEffect(finishedExercise){
        if(finishedExercise){
            navController.navigate(AssistantScreen.ExerciseScreen.name)
            viewModel.resetComplete()
        }
    }

    val exercise = viewModel.currentExercise.observeAsState()
    val currentTime = viewModel.currentTime.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = exercise.value?.name ?: "", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
        Timer(modifier = Modifier
            .fillMaxSize()
            .padding(32.dp), maxTime = exercise.value?.durationSeconds ?: 30, currentTime = currentTime.value)
    }
}

@Preview
@Composable
fun TimeScreenPreview(){
    TimeScreen(rememberNavController(), viewModel(), 1)
}