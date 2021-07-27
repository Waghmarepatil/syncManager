package android.cranberry.syncmanager

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
object DBConstants {

    const val SCHEDULER_TASK_TAG = "myWorkManager"
    const val INTERVAL = 15

    const val COMMAND_INSERT = "insert"
    const val COMMAND_UPDATE = "update"
    const val COMMAND_DELETE = "delete"

    const val TABLE_USER = "user"

    const val TABLE_USER_FIELD_NAME = "name"

    const val STATUS_PENDING = 'p'
    const val STATUS_COMPLETED = 'c'

    const val PRIORITY_HIGH = 1
    const val PRIORITY_MEDIUM = 2
    const val PRIORITY_LOW = 3
}