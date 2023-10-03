package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.C */
class C0789C implements Parcelable.Creator {
    C0789C() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ReadDraftDataAction(parcel, (C0789C) null);
    }

    public Object[] newArray(int i) {
        return new ReadDraftDataAction[i];
    }
}
