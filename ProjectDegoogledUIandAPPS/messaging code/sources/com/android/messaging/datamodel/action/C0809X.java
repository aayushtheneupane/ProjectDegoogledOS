package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.X */
class C0809X implements Parcelable.Creator {
    C0809X() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new UpdateMessagePartSizeAction(parcel, (C0809X) null);
    }

    public Object[] newArray(int i) {
        return new UpdateMessagePartSizeAction[i];
    }
}
