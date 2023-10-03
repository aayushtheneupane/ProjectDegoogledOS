package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: androidx.recyclerview.widget.la */
final class C0576la implements Parcelable.ClassLoaderCreator {
    C0576la() {
    }

    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new RecyclerView.SavedState(parcel, classLoader);
    }

    public Object[] newArray(int i) {
        return new RecyclerView.SavedState[i];
    }

    public Object createFromParcel(Parcel parcel) {
        return new RecyclerView.SavedState(parcel, (ClassLoader) null);
    }
}
