package com.android.messaging.sms;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.sms.q */
class C1021q implements Parcelable.Creator {
    C1021q() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DatabaseMessages$MmsPart(parcel, (C1016l) null);
    }

    public Object[] newArray(int i) {
        return new DatabaseMessages$MmsPart[i];
    }
}
