package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.LinearLayoutManager;

/* renamed from: androidx.recyclerview.widget.D */
final class C0527D implements Parcelable.Creator {
    C0527D() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new LinearLayoutManager.SavedState(parcel);
    }

    public Object[] newArray(int i) {
        return new LinearLayoutManager.SavedState[i];
    }
}
