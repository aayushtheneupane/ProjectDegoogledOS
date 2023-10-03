package androidx.viewpager.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.viewpager.widget.ViewPager;

/* renamed from: androidx.viewpager.widget.n */
final class C0629n implements Parcelable.ClassLoaderCreator {
    C0629n() {
    }

    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new ViewPager.SavedState(parcel, classLoader);
    }

    public Object[] newArray(int i) {
        return new ViewPager.SavedState[i];
    }

    public Object createFromParcel(Parcel parcel) {
        return new ViewPager.SavedState(parcel, (ClassLoader) null);
    }
}
