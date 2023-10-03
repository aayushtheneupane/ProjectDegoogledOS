package p000;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ewp */
/* compiled from: PG */
public final class ewp extends eqv {
    public static final Parcelable.Creator CREATOR = new ewq();

    /* renamed from: a */
    public final ejq f9140a;

    /* renamed from: b */
    public final eqs f9141b;

    /* renamed from: c */
    private final int f9142c;

    public ewp() {
        this(1, new ejq(8, (PendingIntent) null), (eqs) null);
    }

    public ewp(int i, ejq ejq, eqs eqs) {
        this.f9142c = i;
        this.f9140a = ejq;
        this.f9141b = eqs;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f9142c);
        abj.m97a(parcel, 2, (Parcelable) this.f9140a, i);
        abj.m97a(parcel, 3, (Parcelable) this.f9141b, i);
        abj.m92a(parcel, a);
    }
}
