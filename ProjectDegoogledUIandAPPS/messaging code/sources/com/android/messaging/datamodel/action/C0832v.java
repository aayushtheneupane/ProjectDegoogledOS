package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.v */
class C0832v implements Parcelable.Creator {
    C0832v() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new MarkAsSeenAction(parcel, (C0832v) null);
    }

    public Object[] newArray(int i) {
        return new MarkAsSeenAction[i];
    }
}
