package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.m */
class C0823m implements Parcelable.Creator {
    C0823m() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DumpDatabaseAction(parcel, (C0823m) null);
    }

    public Object[] newArray(int i) {
        return new DumpDatabaseAction[i];
    }
}
