package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.l */
class C0822l implements Parcelable.Creator {
    C0822l() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DownloadMmsAction(parcel, (C0822l) null);
    }

    public Object[] newArray(int i) {
        return new DownloadMmsAction[i];
    }
}
