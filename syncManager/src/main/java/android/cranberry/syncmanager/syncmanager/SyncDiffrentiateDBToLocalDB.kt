package android.cranberry.syncmanager.syncmanager

import android.content.Context
import android.cranberry.syncmanager.diff_database.DBConstants
import android.cranberry.syncmanager.diff_database.SyncDBEntity
import android.cranberry.syncmanager.diff_database.SyncDatabase
import android.cranberry.syncmanager.mobile_database.RealmManager
import android.cranberry.syncmanager.mobile_database.Users
import android.cranberry.syncmanager.utility.Logger
import android.cranberry.syncmanager.utility.Utils
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.realm.Realm
import org.json.JSONObject
import java.util.*


/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 29/7/21
 */
class SyncDiffrentiateDBToLocalDB(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    //init realm
    lateinit var realm: Realm

    override suspend fun doWork(): Result {
        Logger.log("INSIDE TO SYNC LOCAL WITH REALM")
        realm = Realm.getDefaultInstance()
        val completedTask = fetchCompletedTask()
        loadDataInLocalDB(completedTask)
        return Result.success()
    }

    /**
     * To load data in local db ie realm
     */
    private fun loadDataInLocalDB(completedTask: List<SyncDBEntity>) {
        for (element in completedTask) {
            when(element.table){
                DBConstants.TABLE_USER -> {
                    when(element.command){
                        DBConstants.COMMAND_INSERT,
                        DBConstants.COMMAND_UPDATE ->{
                            val isSuccess = RealmManager.insertOrUpdateUser(element.field,realm)
                            if (isSuccess)
                                SyncManager.syncManager!!.deleteSyncDataEntry(element.id)
                        }
                        DBConstants.COMMAND_DELETE -> {
                            val isSuccess = RealmManager.deleteUser(JSONObject(element.field),realm)
                            if (isSuccess)
                                SyncManager.syncManager!!.deleteSyncDataEntry(element.id)
                        }
                    }

                }
            }
        }
    }

    /**
     * To fetch the completed task/records
     * @return : list of completed task
     */
    private fun fetchCompletedTask(): List<SyncDBEntity> {
        val diffDbDao = SyncDatabase.getInstance(applicationContext).dao
        return diffDbDao.getSyncedRecords(DBConstants.STATUS_APPROVED, true)
    }
}