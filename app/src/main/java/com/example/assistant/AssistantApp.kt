package com.example.assistant

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assistant.screens.ChatScreen
import com.example.assistant.viewModel.ChatViewModel

enum class AssistantScreen {
    ChatScreen
}

@Composable
fun AssistantApp (
    navController: NavHostController = rememberNavController()
) {

    val viewModel: ChatViewModel = viewModel()

    NavHost(navController = navController, startDestination = AssistantScreen.ChatScreen.name) {
        composable(AssistantScreen.ChatScreen.name) {
            ChatScreen(chatViewModel = viewModel)
        }
    }
}