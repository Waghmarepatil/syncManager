package android.cranberry.networksync

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
object WorkManagerScheduler {
    fun refreshPeriodicWork(context: Context) {
        Log.d("TAGG","INSIDE TO SCHEDULER:")

        //define constraints
        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val refreshCpnWork = PeriodicWorkRequest.Builder(
            SyncScheduleWorker::class.java,
            15, TimeUnit.MINUTES) // repeat interval
            .setInitialDelay(5, TimeUnit.MINUTES) // initial delay
            .setConstraints(myConstraints) // apply constraints
            .addTag(DBConstants.SCHEDULER_TASK_TAG) // tag to uniquely identify task in future
            .build()


        WorkManager.getInstance(context).enqueueUniquePeriodicWork("myWorkManager",
            ExistingPeriodicWorkPolicy.KEEP, refreshCpnWork)


    }

}