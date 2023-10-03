package androidx.legacy.app;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.legacy.app.a */
final class C0441a implements Parcelable.Creator {
    C0441a() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new FragmentTabHost$SavedState(parcel);
    }

    public Object[] newArray(int i) {
        return new FragmentTabHost$SavedState[i];
    }
}
