package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.I */
class C0795I implements Parcelable.Creator {
    C0795I() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ResendMessageAction(parcel, (C0795I) null);
    }

    public Object[] newArray(int i) {
        return new ResendMessageAction[i];
    }
}
