package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.fragment.app.L */
final class C0393L implements Parcelable.Creator {
    C0393L() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new FragmentState(parcel);
    }

    public Object[] newArray(int i) {
        return new FragmentState[i];
    }
}
