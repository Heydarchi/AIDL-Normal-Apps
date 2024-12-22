package com.aospinsight.aidlserver

import android.os.IBinder
import com.aospinsight.myaidl.Address
import com.aospinsight.myaidl.IUserInterface
import com.aospinsight.myaidl.User

class UserInterface : IUserInterface.Stub() {
    override fun asBinder(): IBinder {
        return this
    }

    override fun getUser(userId: Int): User {
        lateinit var addr : Address
        addr.street = "Nordstan"
        addr.city = "Gothenburg"
        addr.postalCode = "41105"

        lateinit var user : User
        user.id = userId
        user.name = "John Doe"
        user.address = addr

        return user
    }

    override fun updateUser(user: User?) {
        if (user != null) {
            println("Updated user: ${user.name}")
        }
    }

    override fun logEvent(log: String?) {
        println("Logged event: $log")
    }
}