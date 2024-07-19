package com.example.login_register_project_2307

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class AssistantScreen() {
    Registration,
    Login
}

@Composable
fun AssistantApp (
    navController: NavHostController = rememberNavController()
) {

//    val viewModel: ChatViewModel = viewModel()

    NavHost(navController = navController, startDestination = AssistantScreen.Registration.name) {
        composable(AssistantScreen.Registration.name) {
//            Registration(chatViewModel = viewModel)
        }
    }
}