package p000;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eua */
/* compiled from: PG */
public final class eua extends eqv {
    public static final Parcelable.Creator CREATOR = new eub();

    /* renamed from: a */
    private final int f9021a;

    /* renamed from: b */
    private final String f9022b;

    /* renamed from: c */
    private final Intent f9023c;

    public eua(int i, String str, Intent intent) {
        this.f9021a = i;
        this.f9022b = str;
        this.f9023c = intent;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 2, this.f9021a);
        abj.m98a(parcel, 3, this.f9022b);
        abj.m97a(parcel, 4, (Parcelable) this.f9023c, i);
        abj.m92a(parcel, a);
    }
}
