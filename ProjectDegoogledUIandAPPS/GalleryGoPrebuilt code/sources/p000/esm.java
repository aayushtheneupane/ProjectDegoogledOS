package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: esm */
/* compiled from: PG */
public final class esm extends eqv {
    public static final Parcelable.Creator CREATOR = new esn();

    /* renamed from: a */
    private final String f8946a;

    /* renamed from: b */
    private final boolean f8947b;

    public esm(String str, boolean z) {
        this.f8946a = str;
        this.f8947b = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f8946a);
        abj.m100a(parcel, 3, this.f8947b);
        abj.m92a(parcel, a);
    }
}
