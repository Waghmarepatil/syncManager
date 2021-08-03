package android.cranberry.syncmanager.syncmanager

import android.content.Context
import android.cranberry.syncmanager.network.NetworkHelper
import android.cranberry.syncmanager.changelog.DBConstants
import android.cranberry.syncmanager.changelog.SyncDBDao
import android.cranberry.syncmanager.changelog.SyncDBEntity
import android.cranberry.syncmanager.changelog.SyncDatabase
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
        if (syncManager == null) {
            syncManager = SyncManager
            AndroidNetworking.initialize(context.applicationContext)
            SyncManager.context = context
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
    fun scheduleWorker(intervalTime: Byte, isToReplace: Boolean) {
        var time = intervalTime
        if (time == 0.toByte()) {
            time = DBConstants.INTERVAL.toByte()
        }
        WorkManagerScheduler().refreshPeriodicWork(context, time, isToReplace)
    }

    /**
     * To check whether all variable is initialized or not
     * @return : validation status
     */
    private fun checkIfAllInitialized(): Boolean {
        var isInitialized = false
        if (dbHelper == null || syncManager == null) {
            throw RuntimeException("Sync Manager must be initialised first")
        } else
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
    fun addRecordInChangeLogDB(
        commandToInsert: String, table: String, field: String,
        priority: Byte, payload: String
    ) {
        if (checkIfAllInitialized()) {
            val entry1 = SyncDBEntity(
                0,
                commandToInsert,
                table,
                field,
                DBConstants.STATUS_PENDING,
                false,
                priority,
                payload.toString()
            )
            dbHelper.insertRecords(entry1)
        }
    }


    /**
     * To delete data entry from differentiate database
     * @param id: primary key of database that data to be deleted
     */
    fun deleteRecordFromChangeLog(id: Long) {
        if (checkIfAllInitialized()) {
            dbHelper.deleteRecord(id)
        }
    }

    /**
     * To delete data entry from differentiate database
     * @param ids: primary key of database that data to be deleted
     */
    fun deleteRecordFromChangeLog(ids: Array<Long>) {
        if (checkIfAllInitialized()) {
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
        if (checkIfAllInitialized()) {
            return dbHelper.getUnSyncedRecords(
                DBConstants.STATUS_PENDING,
                DBConstants.PRIORITY_HIGH.toByte()
            )
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
        if (checkIfAllInitialized()) {
            return dbHelper.getSyncedRecords(DBConstants.STATUS_APPROVED, true)
        }
        return emptyList()
    }

    /**
     * To mark as all record synced
     */
    fun markAllRecordAsSynced() {
        if (checkIfAllInitialized()) {
            dbHelper.markAllRecordSynced(
                DBConstants.STATUS_APPROVED, DBConstants.STATUS_PENDING,
                true
            )
        }
    }


    /**
     * To mark as all record synced
     */
    fun markRecordAsSynced(id: Long, keepOffline: Boolean) {
        if (checkIfAllInitialized()) {
            dbHelper.markAsRecordSynced(id, DBConstants.STATUS_APPROVED, keepOffline)
        }
    }

    /**
     * Used to sync differentiated database to mobile database
     */
    fun syncChangeLogDBToMaster() {
        WorkManagerScheduler().scheduleChangeLogSyncer(true, context)
    }


    /**
     * To execute changes
     */
    fun executeChanges(){

    }

    /**
     * To encrypt data
     * @param plainText: Plain text
     * @return : Cipher text
     */
    fun encryptText(plainText: String): String {
        return ""
    }


    /**
     * To decrypt data
     * @param cipher: cipher text
     * @return : plain text
     */
    fun decryptText(cipher: String): String {
        return ""
    }



    /**
     * To Compress data
     * @param data: data to compress
     * @return : Compressed data
     */
    fun compressData(data: String): String {
        return ""
    }


    /**
     * To De Compress data
     * @param compressedData: compressed data to de compress
     * @return : de compressed data
     */
    fun deCompressData(compressedData: String): String {
        return ""
    }



}