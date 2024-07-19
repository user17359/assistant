package com.example.login_register_project_2307.ui.theme

sealed class Screen(val route:String) {
    object Registration: Screen(route="register")
    object Login:Screen(route="login")
}