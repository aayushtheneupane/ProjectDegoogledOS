package com.android.messaging.sms;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.sms.n */
class C1018n implements Parcelable.Creator {
    C1018n() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DatabaseMessages$LocalDatabaseMessage(parcel, (C1016l) null);
    }

    public Object[] newArray(int i) {
        return new DatabaseMessages$LocalDatabaseMessage[i];
    }
}
