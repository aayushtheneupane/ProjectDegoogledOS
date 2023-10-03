package com.android.messaging.datamodel.data;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.android.messaging.datamodel.data.L */
class C0900L implements Parcelable.Creator {
    C0900L() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ParticipantData(parcel);
    }

    public Object[] newArray(int i) {
        return new ParticipantData[i];
    }
}
