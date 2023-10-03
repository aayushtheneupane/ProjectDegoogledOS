package androidx.coordinatorlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.coordinatorlayout.widget.a */
final class C0296a implements Parcelable.ClassLoaderCreator {
    C0296a() {
    }

    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new CoordinatorLayout$SavedState(parcel, classLoader);
    }

    public Object[] newArray(int i) {
        return new CoordinatorLayout$SavedState[i];
    }

    public Object createFromParcel(Parcel parcel) {
        return new CoordinatorLayout$SavedState(parcel, (ClassLoader) null);
    }
}
