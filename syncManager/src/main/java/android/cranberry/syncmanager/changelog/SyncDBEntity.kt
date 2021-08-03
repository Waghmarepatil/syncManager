package android.cranberry.syncmanager.changelog

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@Entity
data class SyncDBEntity(
    @PrimaryKey(autoGenerate = true) var id: Long, // id of row ie primary key
    @ColumnInfo(name = "action") var command: String?, // action like insert/update/delete
    @ColumnInfo(name = "table") var table: String?, // table name like user/info/..
    @ColumnInfo(name = "field") var field: String?, // json object ie payload
    @ColumnInfo(name = "status") var status: Char?, // status of record ie p/a/r
    //to keep record in local mobile db
    @ColumnInfo(name = "keepOffline", defaultValue = false.toString()) var keepOffline: Boolean?,
    @ColumnInfo(name = "priority") var priority: Byte?, // priority of records
    @ColumnInfo(name = "payload") var payload: String, //option for future use
){
    override fun toString(): String {
        return "DiffDb(id=$id, command=$command, table=$table, field=$field, status=$status, keepOffline=$keepOffline, priority=$priority, payload='$payload')"
    }
}