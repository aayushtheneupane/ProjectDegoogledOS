package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.G */
class C0793G implements Parcelable.Creator {
    C0793G() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ReceiveSmsMessageAction(parcel, (C0793G) null);
    }

    public Object[] newArray(int i) {
        return new ReceiveSmsMessageAction[i];
    }
}
