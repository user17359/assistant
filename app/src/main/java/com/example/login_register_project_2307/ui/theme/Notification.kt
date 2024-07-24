package com.example.login_register_project_2307.ui.theme

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.login_register_project_2307.MainActivity
import com.example.login_register_project_2307.R
import android.app.AlarmManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import android.content.BroadcastReceiver

class Notification(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(title: String, content: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, "Main Channel")
            .setSmallIcon(R.drawable.notifications_24dp_5f6368_fill0_wght400_grad0_opsz24)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }

    fun scheduleNotification(hour: Int, minute: Int, title: String, content: String, requestCode: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("title", title)
            putExtra("content", content)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Powiadomienie"
        val content = intent.getStringExtra("content") ?: "Otrzymałeś nowe powiadomienie z aplikacji"
        val notification = Notification(context)
        notification.showNotification(title, content)
    }
}