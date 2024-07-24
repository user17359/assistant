package com.example.login_register_project_2307.ui.theme

import android.app.Application
import android.app.Notification
import android.app.Notification.VISIBILITY_PUBLIC
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.login_register_project_2307.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
//class MyApplication : Application() {
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//    }
//
//    companion object {
//        private var instance: MyApplication? = null
//
//        fun applicationContext(): Context {
//            return instance!!.applicationContext
//        }
//    }
//}
//var builder = NotificationCompat.Builder(MyApplication.applicationContext(), "First channel ID")
//    .setSmallIcon(R.drawable.notifications_24dp_5f6368_fill0_wght400_grad0_opsz24)
//    .setContentTitle("First message")
//    .setContentText("This is first notification")
//    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//
//fun getNotificationManagerCompat(): NotificationManagerCompat {
//    val context = MyApplication.applicationContext()
//    val notificationManager = NotificationManagerCompat.from(context)
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val name = "Main Channel"
//        val descriptionText = "Channel Description"
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = NotificationChannel("First channel ID", name, importance).apply {
//            description = descriptionText
//        }
//        notificationManager.createNotificationChannel(channel)
//    }
//
//    return notificationManager
//}
//
//fun showSimpleNotification(notificationManager:NotificationManagerCompat,notificationBuilder: NotificationCompat.Builder){
//    notificationManager.notify(1,notificationBuilder.build())
//}
