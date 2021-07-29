package android.cranberry.syncmanager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@Dao
internal interface SyncDBDao {

    //fetch all records
    @Query("SELECT * FROM SyncDBEntity")
    fun getAllRecords(): List<SyncDBEntity>

    //insert new record(s)
    @Insert
    fun insertRecords(vararg arrayOfSyncDBEntities: SyncDBEntity)

    //delete record if payload parse and inserted success
    @Query("DELETE FROM SyncDBEntity WHERE id = :id")
    fun deleteRecord(id: Byte)

    //delete record if payload parse and inserted success
    @Query("DELETE FROM SyncDBEntity WHERE id IN (:id)")
    fun deleteRecords(id: Array<Int>)


    // fetch records according to the status
    @Query("SELECT * from SyncDBEntity WHERE STATUS = :status")
    fun getRecordsByStatus(status: Char) : List<SyncDBEntity>


    // to mark record as completed
    @Query("UPDATE SyncDBEntity SET STATUS = :newStatus WHERE status = :oldStatus ")
    fun markAllRecordSynced(newStatus: Char, oldStatus: Char)


    // to mark record as completed
    @Query("UPDATE SyncDBEntity SET STATUS = :newStatus WHERE id = :id ")
    fun markAsRecordSynced(id: Byte, newStatus: Char)

    // to mark record as completed
    @Query("select * from SyncDBEntity where status = :status and priority = :priority")
    fun getUnSyncedRecords(status: Char, priority: Byte) : List<SyncDBEntity>


    // to mark record as completed
    @Query("select * from SyncDBEntity where status = :status and keepOffline = :keepOffline")
    fun getSyncedRecords(status: Char, keepOffline: Boolean) : List<SyncDBEntity>


}
