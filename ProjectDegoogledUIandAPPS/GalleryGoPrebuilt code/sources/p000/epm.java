package p000;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: epm */
/* compiled from: PG */
public final class epm extends eqv {
    public static final Parcelable.Creator CREATOR = new epn();

    /* renamed from: a */
    public Bundle f8784a;

    /* renamed from: b */
    public ejt[] f8785b;

    /* renamed from: c */
    private int f8786c;

    public epm() {
    }

    public epm(Bundle bundle, ejt[] ejtArr, int i) {
        this.f8784a = bundle;
        this.f8785b = ejtArr;
        this.f8786c = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m95a(parcel, 1, this.f8784a);
        abj.m103a(parcel, 2, (Parcelable[]) this.f8785b, i);
        abj.m114b(parcel, 3, this.f8786c);
        abj.m92a(parcel, a);
    }
}
