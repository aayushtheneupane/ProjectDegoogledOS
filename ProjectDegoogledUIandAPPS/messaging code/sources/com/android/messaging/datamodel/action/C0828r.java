package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.r */
class C0828r implements Parcelable.Creator {
    C0828r() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new HandleLowStorageAction(parcel, (C0828r) null);
    }

    public Object[] newArray(int i) {
        return new HandleLowStorageAction[i];
    }
}
