package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.fragment.app.b */
final class C0409b implements Parcelable.Creator {
    C0409b() {
    }

    public Object createFromParcel(Parcel parcel) {
        return new BackStackState(parcel);
    }

    public Object[] newArray(int i) {
        return new BackStackState[i];
    }
}
