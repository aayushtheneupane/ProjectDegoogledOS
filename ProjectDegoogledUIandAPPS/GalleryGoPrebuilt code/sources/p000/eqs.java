package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: eqs */
/* compiled from: PG */
public final class eqs extends eqv {
    public static final Parcelable.Creator CREATOR = new eqt();

    /* renamed from: a */
    public final ejq f8852a;

    /* renamed from: b */
    public final boolean f8853b;

    /* renamed from: c */
    public final boolean f8854c;

    /* renamed from: d */
    private final int f8855d;

    /* renamed from: e */
    private final IBinder f8856e;

    public eqs(int i, IBinder iBinder, ejq ejq, boolean z, boolean z2) {
        this.f8855d = i;
        this.f8856e = iBinder;
        this.f8852a = ejq;
        this.f8853b = z;
        this.f8854c = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof eqs) {
            eqs eqs = (eqs) obj;
            return this.f8852a.equals(eqs.f8852a) && mo5168a().equals(eqs.mo5168a());
        }
    }

    /* renamed from: a */
    public final eqg mo5168a() {
        IBinder iBinder = this.f8856e;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        if (!(queryLocalInterface instanceof eqg)) {
            return new eqf(iBinder);
        }
        return (eqg) queryLocalInterface;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f8855d);
        abj.m96a(parcel, 2, this.f8856e);
        abj.m97a(parcel, 3, (Parcelable) this.f8852a, i);
        abj.m100a(parcel, 4, this.f8853b);
        abj.m100a(parcel, 5, this.f8854c);
        abj.m92a(parcel, a);
    }
}
