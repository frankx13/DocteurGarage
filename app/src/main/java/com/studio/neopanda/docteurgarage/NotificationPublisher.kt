package com.studio.neopanda.docteurgarage

//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Intent
//import android.content.BroadcastReceiver
//import android.content.Context
//
//
//class MyNotificationPublisher : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        val notificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notification = intent.getParcelableExtra<Notification>(NOTIFICATION)
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val notificationChannel = NotificationChannel(
//                NOTIFICATION_CHANNEL_ID,
//                "NOTIFICATION_CHANNEL_NAME",
//                importance
//            )
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        val id = intent.getIntExtra(NOTIFICATION_ID, 0)
//        notificationManager.notify(id, notification)
//    }
//
//    companion object {
//        var NOTIFICATION_ID = "notification-id"
//        var NOTIFICATION = "notification"
//    }
//}