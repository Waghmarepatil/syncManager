package android.cranberry.syncmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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

            //init db instance
            val diffDbDao = SyncDatabase.getInstance(applicationContext).dao
            // fetch completed task by NDK
            val completedTask = diffDbDao.getRecordsByStatus(DBConstants.STATUS_APPROVED)

            return@withContext Result.Success()
        }
    }

    /**
     * To parse payload and save json object in local Database
     *//*

    private fun parsePayload(diffDb: DiffDb, realm: Realm) {

        //query to access record
        val query: RealmQuery<Users> = realm.where(Users::class.java)
        query.equalTo("id",diffDb.id)


        when(diffDb.command){
            DBConstants.COMMAND_UPDATE,
            DBConstants.COMMAND_INSERT -> {
                // if data already found
                if (query.findAll().size > 0){
                    val users = query.findFirst()
                    val payload = JSONObject(diffDb.payload)
                    users!!.name = payload.getString("name")
                    users.age = payload.getInt("age")
                }else { // data not found create new record
                    val users = realm.createObject(Users::class.java, diffDb.id) as Users
                    val payload = JSONObject(diffDb.payload)
                    users.name = payload.getString("name")
                    users.age = payload.getInt("age")
                }
            }
            DBConstants.COMMAND_DELETE -> {
                query.findFirst()!!.deleteFromRealm()
            }
        }


    }
*/


}