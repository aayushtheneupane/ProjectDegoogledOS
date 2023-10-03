package com.android.messaging.p041ui.attachmentchooser;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.p041ui.attachmentchooser.AttachmentGridView;

/* renamed from: com.android.messaging.ui.attachmentchooser.i */
class C1113i implements Parcelable.Creator {
    C1113i() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new AttachmentGridView.SavedState(parcel, (C1111g) null);
    }

    public Object[] newArray(int i) {
        return new AttachmentGridView.SavedState[i];
    }
}
