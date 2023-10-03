package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.H */
class C0794H implements Parcelable.Creator {
    C0794H() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new RedownloadMmsAction(parcel, (C0794H) null);
    }

    public Object[] newArray(int i) {
        return new RedownloadMmsAction[i];
    }
}
