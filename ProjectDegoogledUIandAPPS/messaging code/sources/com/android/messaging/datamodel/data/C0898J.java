package com.android.messaging.datamodel.data;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.data.J */
class C0898J implements Parcelable.Creator {
    C0898J() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new MessagePartData(parcel);
    }

    public Object[] newArray(int i) {
        return new MessagePartData[i];
    }
}
