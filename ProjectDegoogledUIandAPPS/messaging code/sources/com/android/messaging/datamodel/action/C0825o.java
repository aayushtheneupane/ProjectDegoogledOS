package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.o */
class C0825o implements Parcelable.Creator {
    C0825o() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new GetOrCreateConversationAction(parcel, (C0825o) null);
    }

    public Object[] newArray(int i) {
        return new GetOrCreateConversationAction[i];
    }
}
