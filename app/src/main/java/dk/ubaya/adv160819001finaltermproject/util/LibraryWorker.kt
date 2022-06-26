package dk.ubaya.adv160819001finaltermproject.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class LibraryWorker(val context: Context, val params: WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        NotificationHelper(context)
            .createNotification(inputData.getString("title").toString(),inputData.getString("message").toString())
        return Result.success()
    }
}