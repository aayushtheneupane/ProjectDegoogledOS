package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.J */
class C0796J implements Parcelable.Creator {
    C0796J() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new SendMessageAction(parcel, (C0796J) null);
    }

    public Object[] newArray(int i) {
        return new SendMessageAction[i];
    }
}
