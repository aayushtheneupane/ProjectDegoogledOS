package com.android.messaging.sms;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.sms.p */
class C1020p implements Parcelable.Creator {
    C1020p() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DatabaseMessages$MmsMessage(parcel, (C1016l) null);
    }

    public Object[] newArray(int i) {
        return new DatabaseMessages$MmsMessage[i];
    }
}
