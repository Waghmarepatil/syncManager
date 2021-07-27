package android.cranberry.networksynclibrary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@Dao
interface DiffDbDao {

    //fetch all records
    @Query("SELECT * FROM diffdb")
    fun getAllRecords(): List<DiffDb>

    //insert new record(s)
    @Insert
    fun insertRecords(vararg arrayOfDiffDbs: DiffDb)

    //delete record if payload parse and inserted success
    @Query("DELETE FROM diffdb WHERE id = :id")
    fun deleteRecord(id: Byte)

    //delete record if payload parse and inserted success
    @Query("DELETE FROM diffdb WHERE id IN (:id)")
    fun deleteRecords(id: Array<Int>)


    // fetch records according to the status
    @Query("SELECT * from DIFFDB WHERE STATUS = :status")
    fun getRecordsByStatus(status: Char) : List<DiffDb>


    // to mark record as completed
    @Query("UPDATE DIFFDB SET STATUS = :newStatus WHERE status = :oldStatus ")
    fun markAsRecordSynced(newStatus: Char, oldStatus: Char)

}
