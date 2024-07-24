package com.example.assistant.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assistant.AssistantScreen
import com.example.assistant.elements.BottomNavBar
import com.example.assistant.elements.ExerciseCard
import com.example.assistant.viewModel.ExerciseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(navController: NavController, viewModel: ExerciseViewModel) {

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true){
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getExercisePlan()
            }
        }
    }

    val exercisePlan = viewModel.exercisePlan.observeAsState()

    Scaffold (
        topBar = {
            TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text("Zestaw ćwiczeń")
            }
        )
        },
        bottomBar = { BottomNavBar(selected = 3, navController = navController) }
    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.run { spacedBy(10.dp) }
            ) {
                items(exercisePlan.value ?: listOf()) { exercise ->
                    ExerciseCard(exercise = exercise
                    ) {
                        coroutineScope.launch {
                            OnCardClick(
                                navController,
                                viewModel,
                                exercise.id
                            )
                        }
                    }
                }
            }
        }
    }
}

suspend fun OnCardClick(navController: NavController, viewModel: ExerciseViewModel, id: Int){
    viewModel.getExercise(id)
    navController.navigate(AssistantScreen.TimerScreen.name + "/" + id.toString())
}

@Preview
@Composable
fun ExerciseScreenPreview()
{
    ExerciseScreen(navController = rememberNavController(), viewModel())
}