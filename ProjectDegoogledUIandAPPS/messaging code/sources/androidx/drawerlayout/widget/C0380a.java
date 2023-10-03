package androidx.drawerlayout.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.drawerlayout.widget.a */
final class C0380a implements Parcelable.ClassLoaderCreator {
    C0380a() {
    }

    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new DrawerLayout$SavedState(parcel, classLoader);
    }

    public Object[] newArray(int i) {
        return new DrawerLayout$SavedState[i];
    }

    public Object createFromParcel(Parcel parcel) {
        return new DrawerLayout$SavedState(parcel, (ClassLoader) null);
    }
}
