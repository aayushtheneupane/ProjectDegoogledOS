package android.support.p016v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.i */
final class C0079i implements Parcelable.Creator {
    C0079i() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new MediaBrowserCompat$MediaItem(parcel);
    }

    public Object[] newArray(int i) {
        return new MediaBrowserCompat$MediaItem[i];
    }
}
