package android.support.p016v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.session.r */
final class C0108r implements Parcelable.Creator {
    C0108r() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ParcelableVolumeInfo(parcel);
    }

    public Object[] newArray(int i) {
        return new ParcelableVolumeInfo[i];
    }
}
