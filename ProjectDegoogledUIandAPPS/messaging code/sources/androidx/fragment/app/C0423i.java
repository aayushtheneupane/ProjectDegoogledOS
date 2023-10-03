package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.fragment.app.i */
final class C0423i implements Parcelable.ClassLoaderCreator {
    C0423i() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new Fragment$SavedState(parcel, (ClassLoader) null);
    }

    public Object[] newArray(int i) {
        return new Fragment$SavedState[i];
    }

    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new Fragment$SavedState(parcel, classLoader);
    }
}
