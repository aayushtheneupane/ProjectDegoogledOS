package com.android.messaging.datamodel.data;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.data.P */
class C0904P implements Parcelable.Creator {
    C0904P() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new PendingAttachmentData(parcel);
    }

    public Object[] newArray(int i) {
        return new PendingAttachmentData[i];
    }
}
