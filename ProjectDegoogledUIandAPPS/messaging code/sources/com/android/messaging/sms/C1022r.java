package com.android.messaging.sms;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.sms.r */
class C1022r implements Parcelable.Creator {
    C1022r() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DatabaseMessages$SmsMessage(parcel, (C1016l) null);
    }

    public Object[] newArray(int i) {
        return new DatabaseMessages$SmsMessage[i];
    }
}
