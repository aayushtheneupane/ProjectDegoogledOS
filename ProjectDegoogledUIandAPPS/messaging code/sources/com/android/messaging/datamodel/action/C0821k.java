package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.k */
class C0821k implements Parcelable.Creator {
    C0821k() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DeleteMessageAction(parcel, (C0821k) null);
    }

    public Object[] newArray(int i) {
        return new DeleteMessageAction[i];
    }
}
