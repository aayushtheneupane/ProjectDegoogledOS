package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: ert */
/* compiled from: PG */
public final class ert extends bil implements eru {
    public ert(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    /* renamed from: a */
    public final erf mo5187a(erf erf, String str, int i, erf erf2) {
        erf erf3;
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) erf);
        a.writeString(str);
        a.writeInt(i);
        bin.m2618a(a, (IInterface) erf2);
        Parcel a2 = mo2086a(2, a);
        IBinder readStrongBinder = a2.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            erf3 = queryLocalInterface instanceof erf ? (erf) queryLocalInterface : new erd(readStrongBinder);
        } else {
            erf3 = null;
        }
        a2.recycle();
        return erf3;
    }

    /* renamed from: b */
    public final erf mo5188b(erf erf, String str, int i, erf erf2) {
        erf erf3;
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) erf);
        a.writeString(str);
        a.writeInt(i);
        bin.m2618a(a, (IInterface) erf2);
        Parcel a2 = mo2086a(3, a);
        IBinder readStrongBinder = a2.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            erf3 = queryLocalInterface instanceof erf ? (erf) queryLocalInterface : new erd(readStrongBinder);
        } else {
            erf3 = null;
        }
        a2.recycle();
        return erf3;
    }
}
