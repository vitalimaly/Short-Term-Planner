package com.vitaliimalone.simpletodo.presentation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.vitaliimalone.simpletodo.R

class Notificator(private val context: Context) {
    companion object {
        private const val MESSAGES_CHANNEL_ID = "MESSAGES_CHANNEL_ID"
        private const val MESSAGES_CHANNEL_NAME = "Messages"
    }

    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel(notificationManager)
    }

    private fun createNotificationChannel(notificationManager: NotificationManagerCompat) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MESSAGES_CHANNEL_ID,
                MESSAGES_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "channel.description"
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showMessageNotification() {
        val notification = NotificationCompat.Builder(context, MESSAGES_CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Title")
            .setContentText("Text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(0, notification)
    }
}