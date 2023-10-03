package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.j */
class C0820j implements Parcelable.Creator {
    C0820j() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new DeleteConversationAction(parcel, (C0820j) null);
    }

    public Object[] newArray(int i) {
        return new DeleteConversationAction[i];
    }
}
