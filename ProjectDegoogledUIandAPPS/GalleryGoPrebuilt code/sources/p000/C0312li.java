package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: li */
/* compiled from: PG */
final class C0312li implements Parcelable.ClassLoaderCreator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m14602a(parcel, (ClassLoader) null);
    }

    /* renamed from: a */
    private static final C0313lj m14602a(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return C0313lj.f15200a;
        }
        throw new IllegalStateException("superState must be null");
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return m14602a(parcel, classLoader);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new C0313lj[i];
    }
}
