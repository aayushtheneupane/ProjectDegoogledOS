package android.support.p016v4.p017os;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: android.support.v4.os.d */
final class C0114d implements Parcelable.Creator {
    C0114d() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new ResultReceiver(parcel);
    }

    public Object[] newArray(int i) {
        return new ResultReceiver[i];
    }
}
