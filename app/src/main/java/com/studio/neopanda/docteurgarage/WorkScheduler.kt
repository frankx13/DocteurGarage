package com.studio.neopanda.docteurgarage

//import android.content.Context
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import java.util.concurrent.TimeUnit
//import kotlin.random.Random
//
//class WorkScheduler(
//    val context: Context,
//    workerParams: WorkerParameters
//) : Worker(context, workerParams) {
//
//    override fun doWork(): Result {
//
//        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_car_manager_blue_24dp)
//            .setContentTitle("Scheduled notification")
//            .setContentText("Hello from one-time worker!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(context)) {
//            notify(Random.nextInt(), builder.build())
//        }
//
//        return Result.success()
//    }
//
//}
//
//fun scheduleOneTimeNotification(initialDelay: Long) {
//    val work =
//        OneTimeWorkRequestBuilder<WorkScheduler>()
//            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
//            .addTag(WORK_TAG)
//            .build()
//
//    WorkManager.getInstance(context).enqueue(work)
//}