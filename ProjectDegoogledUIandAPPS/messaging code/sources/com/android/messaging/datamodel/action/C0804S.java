package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.S */
class C0804S implements Parcelable.Creator {
    C0804S() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new UpdateConversationArchiveStatusAction(parcel);
    }

    public Object[] newArray(int i) {
        return new UpdateConversationArchiveStatusAction[i];
    }
}
