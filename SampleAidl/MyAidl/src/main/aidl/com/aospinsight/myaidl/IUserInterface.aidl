package com.aospinsight.myaidl;

import com.aospinsight.myaidl.User;

interface IUserInterface {
    User getUser(int id);
    void updateUser(in User user);
    oneway void logEvent(String event);
}
