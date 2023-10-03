package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.t */
class C0830t implements Parcelable.Creator {
    C0830t() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new LogTelephonyDatabaseAction(parcel, (C0830t) null);
    }

    public Object[] newArray(int i) {
        return new LogTelephonyDatabaseAction[i];
    }
}
