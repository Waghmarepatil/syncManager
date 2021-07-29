package android.cranberry.networksynclibrary

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class SyncDatabases : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"SERVICE EXECUTED",Toast.LENGTH_LONG).show()
        Log.d("TAGGG","SERVICE EXECUTED......")
        return START_STICKY
    }
}
