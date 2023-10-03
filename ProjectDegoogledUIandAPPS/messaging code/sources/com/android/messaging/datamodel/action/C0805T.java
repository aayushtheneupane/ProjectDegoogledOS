package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.action.T */
class C0805T implements Parcelable.Creator {
    C0805T() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new UpdateDestinationBlockedAction(parcel);
    }

    public Object[] newArray(int i) {
        return new UpdateDestinationBlockedAction[i];
    }
}
