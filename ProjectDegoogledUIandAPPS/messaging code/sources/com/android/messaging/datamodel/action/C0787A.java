package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.A */
class C0787A implements Parcelable.Creator {
    C0787A() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ProcessPendingMessagesAction(parcel, (C0835y) null);
    }

    public Object[] newArray(int i) {
        return new ProcessPendingMessagesAction[i];
    }
}
