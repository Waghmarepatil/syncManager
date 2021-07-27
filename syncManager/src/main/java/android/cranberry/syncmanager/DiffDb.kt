package android.cranberry.syncmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@Entity
data class DiffDb(
    @PrimaryKey(autoGenerate = true) var id: Byte,
    @ColumnInfo(name = "command") var command: String?,
    @ColumnInfo(name = "table") var table: String?,
    @ColumnInfo(name = "field") var field: String?,
    @ColumnInfo(name = "status") var status: Char?,
    @ColumnInfo(name = "keepOffline", defaultValue = false.toString()) var keepOffline: Boolean?,
    @ColumnInfo(name = "priority") var priority: Byte?,
    @ColumnInfo(name = "payload") var payload: String,
){
    override fun toString(): String {
        return "DiffDb(id=$id, command=$command, table=$table, field=$field, status=$status, keepOffline=$keepOffline, priority=$priority, payload='$payload')"
    }
}