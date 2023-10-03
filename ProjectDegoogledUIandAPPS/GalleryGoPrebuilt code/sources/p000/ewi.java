package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ewi */
/* compiled from: PG */
public final class ewi extends bil implements ewj {
    public ewi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    /* renamed from: a */
    public final void mo5340a(int i) {
        Parcel a = mo2085a();
        a.writeInt(i);
        mo2088b(7, a);
    }

    /* renamed from: a */
    public final void mo5341a(eqg eqg, int i, boolean z) {
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) eqg);
        a.writeInt(i);
        bin.m2620a(a, z);
        mo2088b(9, a);
    }

    /* renamed from: a */
    public final void mo5342a(ewn ewn, ewh ewh) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) ewn);
        bin.m2618a(a, (IInterface) ewh);
        mo2088b(12, a);
    }
}
