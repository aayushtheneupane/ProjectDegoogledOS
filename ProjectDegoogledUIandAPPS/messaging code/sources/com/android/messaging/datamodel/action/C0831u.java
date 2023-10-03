package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.u */
class C0831u implements Parcelable.Creator {
    C0831u() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new MarkAsReadAction(parcel, (C0831u) null);
    }

    public Object[] newArray(int i) {
        return new MarkAsReadAction[i];
    }
}
