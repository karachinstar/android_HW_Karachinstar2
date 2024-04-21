package ru.gb.android.lession.m20_firebase

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createNotificationChanel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChanel(){
        val name = "ESA notification"
        val description = "I test my notification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val chanel = NotificationChannel(
            NOTIFICATION_CHANEL_ID,
            name,
            importance
        ).apply {
            this.description = description
        }

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(chanel)



    }

    companion object {
        const val NOTIFICATION_CHANEL_ID = "ESA_chanel_id"
    }
}