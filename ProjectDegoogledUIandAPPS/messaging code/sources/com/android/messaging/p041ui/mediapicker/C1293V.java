package com.android.messaging.p041ui.mediapicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.p041ui.mediapicker.GalleryGridView;

/* renamed from: com.android.messaging.ui.mediapicker.V */
class C1293V implements Parcelable.Creator {
    C1293V() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new GalleryGridView.SavedState(parcel, (C1291T) null);
    }

    public Object[] newArray(int i) {
        return new GalleryGridView.SavedState[i];
    }
}
