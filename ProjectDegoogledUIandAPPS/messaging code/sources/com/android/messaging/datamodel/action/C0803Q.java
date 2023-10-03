package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.Q */
class C0803Q implements Parcelable.Creator {
    C0803Q() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new SyncMessagesAction(parcel, (C0803Q) null);
    }

    public Object[] newArray(int i) {
        return new SyncMessagesAction[i];
    }
}
