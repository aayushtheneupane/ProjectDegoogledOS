package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.w */
class C0833w implements Parcelable.Creator {
    C0833w() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ProcessDeliveryReportAction(parcel, (C0833w) null);
    }

    public Object[] newArray(int i) {
        return new ProcessDeliveryReportAction[i];
    }
}
