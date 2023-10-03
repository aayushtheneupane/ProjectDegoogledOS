package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eun */
/* compiled from: PG */
public final class eun extends eqv {
    public static final Parcelable.Creator CREATOR = new euo();

    /* renamed from: a */
    private final byte[] f9052a;

    public eun(byte[] bArr) {
        this.f9052a = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m101a(parcel, 2, this.f9052a);
        abj.m92a(parcel, a);
    }
}
