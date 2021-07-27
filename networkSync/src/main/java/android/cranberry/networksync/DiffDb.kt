package android.cranberry.networksync

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
@Entity
data class DiffDb(
    @PrimaryKey(autoGenerate = true) var id: Byte,
    @Nonnull
    @ColumnInfo(name = "command") var command: String?,
    @Nonnull
    @ColumnInfo(name = "table") var table: String?,
    @Nonnull
    @ColumnInfo(name = "field") var field: String?,
    @Nonnull
    @ColumnInfo(name = "status") var status: Char?,
    @Nonnull
    @ColumnInfo(name = "keepOffline", defaultValue = false.toString()) var keepOffline: Boolean?,
    @Nonnull
    @ColumnInfo(name = "priority") var priority: Byte?,
    @Nonnull
    @ColumnInfo(name = "payload") var payload: String,
){
    override fun toString(): String {
        return "DiffDb(id=$id, command=$command, table=$table, field=$field, status=$status, keepOffline=$keepOffline, priority=$priority, payload='$payload')"
    }
}