package android.cranberry.syncmanager.mobile_database

import android.cranberry.syncmanager.utility.Logger
import android.cranberry.syncmanager.utility.Utils
import io.realm.Realm
import org.json.JSONObject
import java.lang.Exception

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 29/7/21
 */
internal object RealmManager {

    /**
     * Used to insert or update the user
     */
    fun insertOrUpdateUser(field: String?, realm: Realm): Boolean {
        var actionCompleted = false
        actionCompleted = try {
            if (!realm.isInTransaction)
                realm.beginTransaction()
            val gson = Utils.getGson()
            val user = gson!!.fromJson<Users>(field, Users::class.java)
            Logger.log("VALUE OF USER:${user.toString()}")
            realm.insertOrUpdate(user)
            if (realm.isInTransaction)
                realm.commitTransaction()
            true
        }catch (e: Exception){
            false
        }
        return actionCompleted

    }

    /**
     * To get users count
     */
    fun getUserCount(realm: Realm): Byte {
        val query = realm.where(Users::class.java)
        return query.findAll().size.toByte()
    }

    /**
     * To delete user from mobile database
     */
    fun deleteUser(jsonObject: JSONObject, realm: Realm): Boolean {
        var actioCompleted = false
        actioCompleted = try{
            if (!realm.isInTransaction)
                realm.beginTransaction()
            var query = realm.where(Users::class.java).equalTo(
                MobileDBConstants.USER_ID,
                jsonObject.getString(MobileDBConstants.USER_ID))
            var user = query.findFirst()
            user!!.deleteFromRealm()
            if (realm.isInTransaction)
                realm.commitTransaction()
            true
        }catch (e: Exception){
            false
        }
        return actioCompleted
    }

}