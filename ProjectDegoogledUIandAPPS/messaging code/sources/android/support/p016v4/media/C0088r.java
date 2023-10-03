package android.support.p016v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.r */
final class C0088r implements Parcelable.Creator {
    C0088r() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new MediaMetadataCompat(parcel);
    }

    public Object[] newArray(int i) {
        return new MediaMetadataCompat[i];
    }
}
