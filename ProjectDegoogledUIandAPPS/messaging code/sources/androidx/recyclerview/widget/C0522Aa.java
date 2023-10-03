package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* renamed from: androidx.recyclerview.widget.Aa */
final class C0522Aa implements Parcelable.Creator {
    C0522Aa() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new StaggeredGridLayoutManager.SavedState(parcel);
    }

    public Object[] newArray(int i) {
        return new StaggeredGridLayoutManager.SavedState[i];
    }
}
