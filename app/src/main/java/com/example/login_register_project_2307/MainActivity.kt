package com.example.login_register_project_2307

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.login_register_project_2307.ui.theme.Login_register_project_2307Theme
import com.example.login_register_project_2307.ui.theme.Notification
import com.example.login_register_project_2307.ui.theme.NotificationReceiver
import com.example.login_register_project_2307.ui.theme.SetupNavGraph


class MainActivity : ComponentActivity() {
    lateinit var navController:NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login_register_project_2307Theme {
                navController = rememberNavController()
                val context: Context = this
                val notification = Notification(context)
                notification.scheduleNotification(18, 27, "Powiadomienie", "Wstaw zdjęcie śniadania", 0)
                notification.scheduleNotification(18, 28, "Powiadomienie", "Wstaw zdjęcie obiadu", 1)
                notification.scheduleNotification(18, 12, "Powiadomienie", "Wstaw zdjęcie kolacji", 2)
                notification.scheduleNotification(18, 13, "Powiadomienie", "Czy często wykonujesz dodatkowe aktywności?", 3)
                notification.scheduleNotification(18, 14, "Powiadomienie", "Jak często wykonujesz dodatkowe aktywności?", 4)
                notification.scheduleNotification(18, 15, "Powiadomienie", "Podaj wzrost", 5)
                notification.scheduleNotification(18, 16, "Powiadomienie", "Podaj wagę", 6)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(navController = navController, context)
                }
            }
        }
    }
}





