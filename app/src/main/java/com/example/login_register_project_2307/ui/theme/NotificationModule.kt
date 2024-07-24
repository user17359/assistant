package com.example.login_register_project_2307.ui.theme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PRIVATE
import androidx.core.app.NotificationCompat.VISIBILITY_SECRET
import androidx.core.app.NotificationManagerCompat
import com.example.login_register_project_2307.R

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object NotificationModule {
//
//    @Singleton
//    @Provides
//    fun provideNotificationBuilder(
//        @ApplicationContext context: Context
//    ): NotificationCompat.Builder {
//        return NotificationCompat.Builder(context, "Main Channel ID")
//            .setContentTitle("Welcome")
//            .setContentText("YouTube Channel: Stevdza-San")
//            .setSmallIcon(R.drawable.notifications_24dp_5f6368_fill0_wght400_grad0_opsz24)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setVisibility(VISIBILITY_PRIVATE)
//            .setPublicVersion(
//                NotificationCompat.Builder(context, "Main Channel ID")
//                    .setContentTitle("Hidden")
//                    .setContentText("Unlock to see the message.")
//                    .build()
//            )
//    }
//
//    @Singleton
//    @Provides
//    fun provideNotificationManager(
//        @ApplicationContext context: Context
//    ): NotificationManagerCompat {
//        val notificationManager = NotificationManagerCompat.from(context)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                "Main Channel ID",
//                "Main Channel",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//        return notificationManager
//    }
//
//}