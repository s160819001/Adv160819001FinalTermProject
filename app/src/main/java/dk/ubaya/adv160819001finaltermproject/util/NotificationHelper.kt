package dk.ubaya.adv160819001finaltermproject.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.view.MainActivity

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID = "library_channel_id"
    private val NOTIFICATION_ID = 1

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel=
                NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description="Library channel description"
            }
            val notificationManager= context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title:String, message: String){
        createNotificationChannel()
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent= PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_MUTABLE)
//        val icon= BitmapFactory.decodeResource(context.resources, R.drawable.todochar)
        val notification = NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_local_library_24)
//            .setLargeIcon(B)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)
    }
}