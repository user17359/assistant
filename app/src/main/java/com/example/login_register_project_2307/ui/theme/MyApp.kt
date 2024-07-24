package com.example.login_register_project_2307.ui.theme

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel= NotificationChannel(
                "Main Channel",
                "Powiadomienie",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description="Przypomnienie o wykonaniu akcji w aplikacji"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
