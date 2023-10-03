package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.W */
class C0808W implements Parcelable.Creator {
    C0808W() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new UpdateMessageNotificationAction(parcel, (C0808W) null);
    }

    public Object[] newArray(int i) {
        return new UpdateMessageNotificationAction[i];
    }
}
