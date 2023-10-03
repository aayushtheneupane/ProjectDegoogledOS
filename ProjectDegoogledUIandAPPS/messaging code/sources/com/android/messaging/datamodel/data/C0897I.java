package com.android.messaging.datamodel.data;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.data.I */
class C0897I implements Parcelable.Creator {
    C0897I() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new MessageData(parcel);
    }

    public Object[] newArray(int i) {
        return new MessageData[i];
    }
}
