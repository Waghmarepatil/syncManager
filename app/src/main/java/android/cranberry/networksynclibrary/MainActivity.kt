package android.recon.syncmanager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.cranberry.networksynclibrary.R
import android.cranberry.syncmanager.*

import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddToDB.setOnClickListener {
            addDataInDB()
        }

        btnFetchAll.setOnClickListener {
            txtDataFromDB.text = ""
           fetchAllRecordsInDiffDb()
        }

        btnCompletedTask.setOnClickListener {
            markAsCompleteTask()
        }

        scheduleWorker()

        testApi()

    }

    @SuppressLint("HardwareIds")
    private fun testApi() {
/*
        val headers = HashMap<String, String>()
        headers.put("Authorization", "abc")
        headers.put("version", "4.0.2")
        headers.put("imei", Settings.Secure.getString(this.contentResolver,Settings.Secure.ANDROID_ID))

        NetworkHelper.get(
            "http://142.93.216.204:12025/meter/get_status",
            tag = "test",
            priority = Priority.HIGH,
            networkCallback = object : NetworkHelper.NetworkCallback {
                override fun onResponse(response: Any) {
                    when (response){
                        is JSONObject -> Log.d("TAGGG", "API CALL GOT RESPONSE:$response")
                        is JSONArray -> Log.d("TAGGG", "API CALL GOT RESPONSE1:$response")
                        else -> Log.d("TAGGG", "API CALL GOT RESPONSE3:$response")
                    }

                }

                override fun onError(errorCode: Int, errorMessage: String?) {
                    Log.d("TAGGG", "API CALL GOT RESPONSE:" + errorCode+" Message:"+errorMessage)
                }

            },
            headers = headers, isJsonObjectRequest = false, pathParameter = null, queryParameter = null
        )*/
    }

    private fun markAsCompleteTask() {
        Thread{
            SyncManager.syncManager!!.markAllRecordAsSynced()
        }.start()
    }


    /**
     * To schedule the scheduler to work as background service
     */
    private fun scheduleWorker() {
        SyncManager.syncManager!!.scheduleWorker(15)
    }

    /**
     * return false if in settings "Not optimized" and true if "Optimizing battery use"
     */
    fun isBatteryOptimized(context: Context): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val name = context.packageName
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return !powerManager.isIgnoringBatteryOptimizations(name)
        }
        return false
    }

    fun checkBattery(context: Context) {
        if (isBatteryOptimized(context) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            val name = context.resources.getString(R.string.app_name)
            Toast.makeText(
                context,
                "Battery optimization -> All apps -> $name -> Don't optimize",
                Toast.LENGTH_LONG
            ).show()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                context.startActivity(intent)
            }

        }
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun fetchAllRecordsInDiffDb() {
        Thread{
            val listOfCommands = SyncManager.syncManager!!.getAllRecords()
            val totalRecords = "Total records in DB: ${listOfCommands.size}" + "\n"
            val totalPendingRecords = "Total Pending records in DB: " +
                    "${SyncManager.syncManager!!.getUnSyncedRecords().size}" + "\n"
            val totalCompletedRecords = "Total Completed records in DB: " +
                    "${SyncManager.syncManager!!.getSyncedRecords().size}" + "\n"
            runOnUiThread {
                txtDataFromDB.text = txtDataFromDB.text.toString() +"\n" +
                        totalRecords+ totalPendingRecords + totalCompletedRecords
            }
        }.start()
/*
        val realm = Realm.getDefaultInstance()
        val query: RealmQuery<Users> = realm.where(Users::class.java)
        txtDataFromDB.text = txtDataFromDB.text.toString() + "IN REALM TOTAL: ${query.count()}"*/

    }

    fun addDataInDB() {
        Thread {
            val payload = JSONObject()
            payload.put("name","Pramod")
            payload.put("age",27)
            SyncManager.syncManager!!.addToBeSyncDataEntry(DBConstants.COMMAND_INSERT,
            DBConstants.TABLE_USER,payload.toString(),DBConstants.PRIORITY_HIGH.toByte())
        }.start()

    }
}