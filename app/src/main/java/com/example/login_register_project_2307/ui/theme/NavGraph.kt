package com.example.login_register_project_2307.ui.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.login_register_project_2307.Registration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.login_register_project_2307.Login

@Composable
fun SetupNavGraph(
    navController:NavHostController,context:Context
){
    NavHost(navController = navController,
        startDestination = Screen.Registration.route){
        composable(route = Screen.Registration.route){
            Registration(navController=navController)
        }
        composable(route = Screen.Login.route){
            Login(navController=navController,context)
        }
    }

}