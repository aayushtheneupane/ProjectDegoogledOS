package android.support.p016v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.media.s */
final class C0089s implements Parcelable.Creator {
    C0089s() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat());
    }

    public Object[] newArray(int i) {
        return new RatingCompat[i];
    }
}
