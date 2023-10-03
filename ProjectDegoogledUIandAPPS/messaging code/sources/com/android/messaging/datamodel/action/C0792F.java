package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.F */
class C0792F implements Parcelable.Creator {
    C0792F() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ReceiveMmsMessageAction(parcel, (C0792F) null);
    }

    public Object[] newArray(int i) {
        return new ReceiveMmsMessageAction[i];
    }
}
