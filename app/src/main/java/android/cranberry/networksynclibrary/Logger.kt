package android.cranberry.networksynclibrary

import android.cranberry.syncmanager.BuildConfig
import android.util.Log

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 29/7/21
 */
internal object Logger {
    val TAG = Logger::class.java.simpleName
    fun log(message: String){
        if (BuildConfig.DEBUG)
            Log.d(TAG,message)
    }
}