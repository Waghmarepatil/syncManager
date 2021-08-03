package android.cranberry.syncmanager.syncmanager

import android.content.Context
import android.cranberry.syncmanager.changelog.DBConstants
import android.cranberry.syncmanager.changelog.SyncDBEntity
import android.cranberry.syncmanager.changelog.SyncDatabase
import android.cranberry.syncmanager.utility.Logger
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.json.JSONObject


/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 29/7/21
 */
class SyncChangeLogDBToMasterDB(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result {
        return Result.success()
    }

}