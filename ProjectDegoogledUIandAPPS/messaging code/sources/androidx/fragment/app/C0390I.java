package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.fragment.app.I */
final class C0390I implements Parcelable.Creator {
    C0390I() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new FragmentManagerState(parcel);
    }

    public Object[] newArray(int i) {
        return new FragmentManagerState[i];
    }
}
