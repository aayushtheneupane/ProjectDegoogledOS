package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.versionedparcelable.a */
final class C0611a implements Parcelable.Creator {
    C0611a() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ParcelImpl(parcel);
    }

    public Object[] newArray(int i) {
        return new ParcelImpl[i];
    }
}
