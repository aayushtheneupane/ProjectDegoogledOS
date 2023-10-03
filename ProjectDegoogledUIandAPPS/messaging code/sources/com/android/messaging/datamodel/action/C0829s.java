package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.s */
class C0829s implements Parcelable.Creator {
    C0829s() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new InsertNewMessageAction(parcel, (C0829s) null);
    }

    public Object[] newArray(int i) {
        return new InsertNewMessageAction[i];
    }
}
