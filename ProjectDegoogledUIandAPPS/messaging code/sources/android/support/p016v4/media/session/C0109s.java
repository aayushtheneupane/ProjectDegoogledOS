package android.support.p016v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.session.s */
final class C0109s implements Parcelable.Creator {
    C0109s() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat(parcel);
    }

    public Object[] newArray(int i) {
        return new PlaybackStateCompat[i];
    }
}
