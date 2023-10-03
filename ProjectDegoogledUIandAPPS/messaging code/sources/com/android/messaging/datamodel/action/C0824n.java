package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.n */
class C0824n implements Parcelable.Creator {
    C0824n() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new FixupMessageStatusOnStartupAction(parcel, (C0824n) null);
    }

    public Object[] newArray(int i) {
        return new FixupMessageStatusOnStartupAction[i];
    }
}
