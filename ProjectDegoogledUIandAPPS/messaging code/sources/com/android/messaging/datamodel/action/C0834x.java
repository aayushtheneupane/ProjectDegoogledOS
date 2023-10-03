package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.x */
class C0834x implements Parcelable.Creator {
    C0834x() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ProcessDownloadedMmsAction(parcel, (C0834x) null);
    }

    public Object[] newArray(int i) {
        return new ProcessDownloadedMmsAction[i];
    }
}
