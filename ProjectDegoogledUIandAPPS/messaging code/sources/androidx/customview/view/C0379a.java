package androidx.customview.view;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.customview.view.a */
final class C0379a implements Parcelable.ClassLoaderCreator {
    C0379a() {
    }

    public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return AbsSavedState.EMPTY_STATE;
        }
        throw new IllegalStateException("superState must be null");
    }

    public Object[] newArray(int i) {
        return new AbsSavedState[i];
    }

    /* renamed from: createFromParcel  reason: collision with other method in class */
    public Object m4703createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return AbsSavedState.EMPTY_STATE;
        }
        throw new IllegalStateException("superState must be null");
    }

    public Object createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, (ClassLoader) null);
    }
}
