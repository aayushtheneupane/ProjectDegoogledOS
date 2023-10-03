package android.support.p016v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p016v4.media.session.PlaybackStateCompat;

/* renamed from: android.support.v4.media.session.t */
final class C0110t implements Parcelable.Creator {
    C0110t() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat.CustomAction(parcel);
    }

    public Object[] newArray(int i) {
        return new PlaybackStateCompat.CustomAction[i];
    }
}
