package android.cranberry.networksynclibrary

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.realm.Realm
import io.realm.RealmQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@SuppressLint("RestrictedApi")
class SyncScheduleWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("TAGG", "INSIDE SYNC SCHEDULER:" + System.currentTimeMillis())
        return withContext(Dispatchers.IO){



            //init db instance
            val diffDbDao = DiffDatabase.getInstance(applicationContext).dao
            // fetch completed task by NDK
            val completedTask = diffDbDao.getRecordsByStatus(DBConstants.STATUS_COMPLETED)
            Log.d("TAGG", "INSIDE SYNC SCHEDULER TOTAL COMPLETED:${completedTask.size}")
/*

            val realm = Realm.getDefaultInstance()
            if (!realm.isInTransaction)
                realm.beginTransaction()

            for (diffDb in completedTask){
                //insert payload in local DB i.e Realm
                parsePayload(diffDb,realm)
                //delete completed task/payload from Diff DB
                diffDbDao.delete(diffDb.id)
            }

            if (realm.isInTransaction)
                realm.commitTransaction()

*/




            return@withContext Result.Success()
        }
    }

    /**
     * To parse payload and save json object in local Database
     */
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


}