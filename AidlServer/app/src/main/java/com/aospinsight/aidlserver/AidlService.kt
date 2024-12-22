package com.aospinsight.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.aospinsight.myaidl.IUserInterface

class AidlService : Service() {

    private val userInterface : IUserInterface = UserInterface()

    override fun onBind(intent: Intent): IBinder {

        return userInterface.asBinder()
    }
}