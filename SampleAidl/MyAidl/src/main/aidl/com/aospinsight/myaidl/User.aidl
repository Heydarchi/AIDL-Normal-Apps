package com.aospinsight.myaidl;

import com.aospinsight.myaidl.Address;
import com.aospinsight.myaidl.UserStatus;

parcelable User {
    int id;
    String name;
    Address address;
    UserStatus status;
}
