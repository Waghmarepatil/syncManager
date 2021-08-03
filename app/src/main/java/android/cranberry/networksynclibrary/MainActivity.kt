package android.recon.syncmanager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.cranberry.networksynclibrary.R
import android.cranberry.networksynclibrary.UUIDGenerator
import android.cranberry.syncmanager.changelog.DBConstants
import android.cranberry.syncmanager.syncmanager.SyncManager

import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
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
/*

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

        btnUpdate.setOnClickListener {
            updateRecords()
        }

        btnDelete.setOnClickListener {
            deleteRecords()
        }

        scheduleWorker()

        testApi()
*/

    }

/*
    private fun deleteRecords() {
        Thread{
            SyncManager.syncManager!!.addToBeSyncDataEntry(DBConstants.COMMAND_DELETE,
                DBConstants.TABLE_USER,"{\"name\":\"Pramod DADA\",\"id\":\"8e9f1357-0a4a-412b-b1fb-91394bb98872\"}",DBConstants.PRIORITY_HIGH.toByte())
        }.start()
    }

    private fun updateRecords() {
        Thread{
            SyncManager.syncManager!!.addToBeSyncDataEntry(DBConstants.COMMAND_UPDATE,
            DBConstants.TABLE_USER,"{\"name\":\"Pramod DADA 2\",\"id\":\"7594b0ef-4bf9-4355-84df-88dee8ecf49c\"}",DBConstants.PRIORITY_HIGH.toByte())
        }.start()
    }

    @SuppressLint("HardwareIds")
    private fun testApi() {
    }

    private fun markAsCompleteTask() {
        Thread{
            SyncManager.syncManager!!.markAllRecordAsSynced()
        }.start()
    }


    */
/**
     * To schedule the scheduler to work as background service
     *//*

    private fun scheduleWorker() {
        SyncManager.syncManager!!.scheduleWorker(15,true)
        SyncManager.syncManager!!.syncChangeLogDBToMaster()
    }

    */
/**
     * return false if in settings "Not optimized" and true if "Optimizing battery use"
     *//*

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

            val totalInRealm = "Total objects in realm: "+SyncManager.syncManager!!.getUserCount()
            val records = SyncManager.syncManager!!.getAllRecords()
            for (element in records){
                android.cranberry.networksynclibrary.Logger.log("RECORD IN DIFF DB:${element.toString()}")
            }

            val usersList = SyncManager.syncManager!!.getUsers()
            usersList!!.forEach {
                android.cranberry.networksynclibrary.Logger.log("RECORD IN MOBILE DB:${it.toString()}")
            }

            runOnUiThread {
                txtDataFromDB.text = txtDataFromDB.text.toString() +"\n" +
                        totalRecords+ totalPendingRecords + totalCompletedRecords + totalInRealm
            }
        }.start()
    }

    fun addDataInDB() {
        Thread {
            val payload = JSONObject()
            payload.put("name","Pramod")
            payload.put("age",27)
            payload.put("id",UUIDGenerator.randomUUID(5))
            SyncManager.syncManager!!.addToBeSyncDataEntry(
                DBConstants.COMMAND_INSERT,
            DBConstants.TABLE_USER,payload.toString(), DBConstants.PRIORITY_HIGH.toByte())
        }.start()

        android.cranberry.networksynclibrary.Logger.log("ADDED IN DB")

    }

*/


}