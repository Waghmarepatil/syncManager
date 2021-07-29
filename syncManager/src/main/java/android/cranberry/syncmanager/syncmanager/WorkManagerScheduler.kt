package android.cranberry.syncmanager.syncmanager

import android.content.Context
import android.cranberry.syncmanager.diff_database.DBConstants
import androidx.work.*
import java.util.concurrent.TimeUnit

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
internal class WorkManagerScheduler {
    /**
     * To schedule the sync
     */
    fun refreshPeriodicWork(context: Context, repeatInterval: Byte, isToReplace: Boolean) {

        val wm = WorkManager.getInstance(context.applicationContext)
        val future = wm.getWorkInfosByTag(DBConstants.SCHEDULER_TASK_TAG)
        val list = future.get()
        // start only if no such tasks present
        if (list == null || list.size == 0 || isToReplace) {

        } else {
            return
        }
        //define constraints
        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val refreshCpnWork = PeriodicWorkRequest.Builder(
            SyncScheduleWorker::class.java,
            repeatInterval.toLong(), TimeUnit.MINUTES) // repeat interval
            .setInitialDelay(5, TimeUnit.MINUTES) // initial delay
            .setConstraints(myConstraints) // apply constraints
            .addTag(DBConstants.SCHEDULER_TASK_TAG) // tag to uniquely identify task in future
            .build()

        val policy = if(isToReplace) ExistingPeriodicWorkPolicy.REPLACE else ExistingPeriodicWorkPolicy.KEEP
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            DBConstants.SCHEDULER_TASK_TAG,
            policy, refreshCpnWork)

    }

    /**
     * To sync diff db into realm
     */
    fun setDiffDbSyncer(isToReplace: Boolean, context: Context){
        //define constraints
        val myConstraints = Constraints.Builder()
            .build()

        val refreshCpnWork = OneTimeWorkRequest.Builder(
            SyncDiffrentiateDBToLocalDB::class.java)
            //.setInitialDelay(5, TimeUnit.MINUTES) // initial delay
            .setConstraints(myConstraints) // apply constraints
            .addTag(DBConstants.SCHEDULER_TASK_TAG) // tag to uniquely identify task in future
            .build()

        val policy = if(isToReplace) ExistingWorkPolicy.REPLACE else ExistingWorkPolicy.KEEP
        WorkManager.getInstance(context).beginUniqueWork(
            DBConstants.SCHEDULER_TASK_TAG,
            policy, refreshCpnWork).enqueue()

    }

}