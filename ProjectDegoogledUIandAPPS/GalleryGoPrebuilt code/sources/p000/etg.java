package p000;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: etg */
/* compiled from: PG */
public final class etg extends eqv {
    public static final Parcelable.Creator CREATOR = new eth();

    /* renamed from: a */
    private final String f8984a;

    /* renamed from: b */
    private final String f8985b;

    /* renamed from: c */
    private final String f8986c;

    /* renamed from: d */
    private final String f8987d;

    public etg(String str, String str2, String str3, String str4) {
        this.f8984a = str;
        this.f8985b = str2;
        this.f8986c = str4;
        this.f8987d = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, this.f8984a);
        abj.m98a(parcel, 3, this.f8985b);
        abj.m98a(parcel, 4, this.f8986c);
        abj.m98a(parcel, 5, this.f8987d);
        abj.m92a(parcel, a);
    }
}
