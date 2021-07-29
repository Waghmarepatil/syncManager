package android.cranberry.syncmanager.syncmanager

import android.annotation.SuppressLint
import android.content.Context
import android.cranberry.syncmanager.diff_database.DBConstants
import android.cranberry.syncmanager.diff_database.SyncDatabase
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@SuppressLint("RestrictedApi")
internal class SyncScheduleWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO){
            //todo : api call and store result in differentiate db
            //init db instance
            val diffDbDao = SyncDatabase.getInstance(applicationContext).dao
            // fetch completed task by NDK
            val completedTask = diffDbDao.getUnSyncedRecords(
                DBConstants.STATUS_PENDING,
                DBConstants.PRIORITY_HIGH.toByte())
            for (element in completedTask){
                diffDbDao.markAsRecordSynced(element.id, DBConstants.STATUS_APPROVED,true)
            }

            WorkManagerScheduler().setDiffDbSyncer(true,applicationContext)

            return@withContext Result.Success()
        }
    }
}