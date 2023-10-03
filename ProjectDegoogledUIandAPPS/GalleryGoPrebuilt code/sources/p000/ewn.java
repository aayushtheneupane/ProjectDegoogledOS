package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ewn */
/* compiled from: PG */
public final class ewn extends eqv {
    public static final Parcelable.Creator CREATOR = new ewo();

    /* renamed from: a */
    private final int f9138a;

    /* renamed from: b */
    private final eqq f9139b;

    public ewn(int i, eqq eqq) {
        this.f9138a = i;
        this.f9139b = eqq;
    }

    public ewn(eqq eqq) {
        this(1, eqq);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f9138a);
        abj.m97a(parcel, 2, (Parcelable) this.f9139b, i);
        abj.m92a(parcel, a);
    }
}
