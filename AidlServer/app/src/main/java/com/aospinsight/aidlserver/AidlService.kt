package com.aospinsight.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.aospinsight.myaidl.IUserInterface
import android.util.Log
class AidlService : Service() {

    val TAG = "AidlService"
    private val userInterface : IUserInterface = UserInterface()

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG,"onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(TAG,"onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG,"onDestroy")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.v(TAG,"onUnbind")
        return super.onUnbind(intent)
    }

    override fun onBind(intent: Intent): IBinder {

        Log.v(TAG,"onBind")
        return userInterface.asBinder()
    }
}