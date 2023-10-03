package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.B */
class C0788B implements Parcelable.Creator {
    C0788B() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ProcessSentMessageAction(parcel, (C0788B) null);
    }

    public Object[] newArray(int i) {
        return new ProcessSentMessageAction[i];
    }
}
