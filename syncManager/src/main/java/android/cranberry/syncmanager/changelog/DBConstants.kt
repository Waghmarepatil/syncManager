package android.cranberry.syncmanager.changelog

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
object DBConstants {

    //tag for work manager for identify and extract for future use
    const val SCHEDULER_TASK_TAG = "myWorkManager"
    const val SCHEDULER_TASK_EXECUTE_CLASS_NAME = "executableClass"
    const val INTERVAL = 15 // default repeat interval time in MINUTE

    const val COMMAND_INSERT = "insert" // command insert to identify the record type
    const val COMMAND_UPDATE = "update" // command update to identify the record type
    const val COMMAND_DELETE = "delete" // command delete to identify the record type

    const val TABLE_USER = "Users" // table name to identify the action on which table

    //record status used to identify the record completion status
    //ie if added initially by user the its 'p'
    //when api called with records then marked as 'a' if approved by server 'r' if rejected by server
    const val STATUS_PENDING = 'p' // record status pending
    const val STATUS_APPROVED = 'a' // record status approved
    const val STATUS_REJECTED = 'r' // record status rejected

    // record priority to identify which record have which priority
    //according to priority the api will take care which record should execute first
    const val PRIORITY_HIGH = 1 // priority high
    const val PRIORITY_MEDIUM = 2 // priority medium
    const val PRIORITY_LOW = 3 // priority low
}