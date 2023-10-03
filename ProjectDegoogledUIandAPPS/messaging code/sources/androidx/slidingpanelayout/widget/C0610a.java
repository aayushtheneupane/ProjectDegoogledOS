package androidx.slidingpanelayout.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.slidingpanelayout.widget.a */
final class C0610a implements Parcelable.ClassLoaderCreator {
    C0610a() {
    }

    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SlidingPaneLayout$SavedState(parcel, (ClassLoader) null);
    }

    public Object[] newArray(int i) {
        return new SlidingPaneLayout$SavedState[i];
    }

    public Object createFromParcel(Parcel parcel) {
        return new SlidingPaneLayout$SavedState(parcel, (ClassLoader) null);
    }
}
