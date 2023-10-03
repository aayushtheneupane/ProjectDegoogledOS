package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.fragment.app.M */
final class C0394M implements Parcelable.Creator {
    C0394M() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new FragmentTabHost$SavedState(parcel);
    }

    public Object[] newArray(int i) {
        return new FragmentTabHost$SavedState[i];
    }
}
