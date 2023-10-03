package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.Y */
class C0810Y implements Parcelable.Creator {
    C0810Y() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new WriteDraftMessageAction(parcel, (C0810Y) null);
    }

    public Object[] newArray(int i) {
        return new WriteDraftMessageAction[i];
    }
}
