package com.example.assistant

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assistant.screens.ChatScreen
import com.example.assistant.screens.ExerciseScreen
import com.example.assistant.screens.TimeScreen
import com.example.assistant.viewModel.ChatViewModel
import com.example.assistant.viewModel.ExerciseViewModel

enum class AssistantScreen {
    ChatScreen,
    ExerciseScreen,
    TimerScreen,
    ProfileScreen,
    AwardsScreen,
    CalendarScreen,
    GameScreen
}

@Composable
fun AssistantApp (
    navController: NavHostController = rememberNavController()
) {

    val chatViewModel: ChatViewModel = viewModel()
    val exerciseViewModel: ExerciseViewModel = viewModel()

    NavHost(navController = navController, startDestination = AssistantScreen.ChatScreen.name) {
        composable(AssistantScreen.ChatScreen.name) {
            ChatScreen(chatViewModel = chatViewModel, navController = navController)
        }
        composable(AssistantScreen.ExerciseScreen.name) {
            ExerciseScreen(navController = navController, exerciseViewModel)
        }
        composable(AssistantScreen.TimerScreen.name + "/{exerciseId}") { backStackEntry ->
            TimeScreen(navController = navController, exerciseViewModel, backStackEntry.arguments?.getString("exerciseId")!!.toInt())
        }
        composable(AssistantScreen.ProfileScreen.name) {
            ChatScreen(chatViewModel = chatViewModel, navController = navController)
        }
        composable(AssistantScreen.AwardsScreen.name) {
            ChatScreen(chatViewModel = chatViewModel, navController = navController)
        }
        composable(AssistantScreen.CalendarScreen.name) {
            ChatScreen(chatViewModel = chatViewModel, navController = navController)
        }
        composable(AssistantScreen.GameScreen.name) {
            ChatScreen(chatViewModel = chatViewModel, navController = navController)
        }
    }
}