package android.cranberry.syncmanager

import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 27/7/21
 */
object SyncManager {
    var syncManager: SyncManager? = null

    private lateinit var dbHelper: SyncDBDao
    private lateinit var networkHelper: NetworkHelper
    lateinit var context: Context

    @Synchronized
    fun getInstance(context: Context): SyncDatabase? {
        if (SyncManager.syncManager == null) {
            SyncManager.syncManager = SyncManager
            AndroidNetworking.initialize(context.applicationContext)
            this.context = context
            dbHelper = SyncDatabase.getInstance(context).dao
        }
        return SyncDatabase.instance
    }


    /**
     * TO init the objects
     */
    @JvmStatic
    fun init(context: Context) {
        getInstance(context)
    }

    /**
     * To schedule work manager
     */
    fun scheduleWorker(intervalTime: Byte){
        var time = intervalTime
        if (time == 0.toByte()) {
            time = DBConstants.INTERVAL.toByte()
        }
        WorkManagerScheduler().refreshPeriodicWork(context, time)
    }

    /**
     * To check whether all variable is initialized or not
     * @return : validation status
     */
    private fun checkIfAllInitialized(): Boolean {
        var isInitialized = false
        if (dbHelper == null || syncManager == null){
            throw RuntimeException("Sync Manager must be initialised first")
        }else
            isInitialized = true
        return isInitialized
    }


    /**
     * To get all records from differentiate database
     * @return : List of database entity
     */
    fun getAllRecords(): List<SyncDBEntity> {
        if (checkIfAllInitialized())
            return dbHelper.getAllRecords()
        return emptyList()
    }


    /**
     * To add entry into differentiate database that
     * @param dbEntity: Entry to add
     */
    fun addToBeSyncDataEntry(
        commandToInsert: String, table: String, field: String,
        priority: Byte/*, payload: String*/
    ){
        if (checkIfAllInitialized()){
            val entry1 = SyncDBEntity(
                0,
                commandToInsert,
                table,
                field,
                DBConstants.STATUS_PENDING,
                false,
                priority,
                /*payload.toString()*/""
            )
            dbHelper.insertRecords(entry1)
        }
    }


    /**
     * To delete data entry from differentiate database
     * @param id: primary key of database that data to be deleted
     */
    fun deleteSyncDataEntry(id: Byte){
        if (checkIfAllInitialized()){
            dbHelper.deleteRecord(id)
        }
    }

    /**
     * To delete data entry from differentiate database
     * @param ids: primary key of database that data to be deleted
     */
    fun deleteSyncDataEntry(ids: Array<Int>){
        if (checkIfAllInitialized()){
            dbHelper.deleteRecords(ids)
        }
    }

    /**
     * Get all records by status
     * @param status :
     *        p -> To fetch all records tobe synced records
     *        c -> TO fetch all records that is synced with server
     */
    fun getUnSyncedRecords(): List<SyncDBEntity> {
        if (checkIfAllInitialized()){
            return dbHelper.getUnSyncedRecords(DBConstants.STATUS_PENDING, DBConstants.PRIORITY_HIGH.toByte())
        }
        return emptyList()
    }


    /**
     * Get all records by status
     * @param status :
     *        p -> To fetch all records tobe synced records
     *        c -> TO fetch all records that is synced with server
     */
    fun getSyncedRecords(): List<SyncDBEntity> {
        if (checkIfAllInitialized()){
            return dbHelper.getSyncedRecords(DBConstants.STATUS_APPROVED,true)
        }
        return emptyList()
    }

    /**
     * To mark as all record synced
     */
    fun markAllRecordAsSynced(){
        if (checkIfAllInitialized()){
            dbHelper.markAllRecordSynced(DBConstants.STATUS_APPROVED, DBConstants.STATUS_PENDING)
        }
    }


    /**
     * To mark as all record synced
     */
    fun markRecordAsSynced(id: Byte){
        if (checkIfAllInitialized()){
            dbHelper.markAsRecordSynced(id, DBConstants.STATUS_APPROVED)
        }
    }

}