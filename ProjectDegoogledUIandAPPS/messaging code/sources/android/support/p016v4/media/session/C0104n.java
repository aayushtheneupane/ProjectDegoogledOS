package android.support.p016v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.session.n */
final class C0104n implements Parcelable.Creator {
    C0104n() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new MediaSessionCompat$QueueItem(parcel);
    }

    public Object[] newArray(int i) {
        return new MediaSessionCompat$QueueItem[i];
    }
}
