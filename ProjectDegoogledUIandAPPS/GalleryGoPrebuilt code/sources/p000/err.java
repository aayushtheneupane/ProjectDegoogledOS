package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: err */
/* compiled from: PG */
public final class err extends bil implements ers {
    public err(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    /* renamed from: a */
    public final erf mo5183a(erf erf, String str, int i) {
        erf erf2;
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) erf);
        a.writeString(str);
        a.writeInt(i);
        Parcel a2 = mo2086a(2, a);
        IBinder readStrongBinder = a2.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            erf2 = queryLocalInterface instanceof erf ? (erf) queryLocalInterface : new erd(readStrongBinder);
        } else {
            erf2 = null;
        }
        a2.recycle();
        return erf2;
    }

    /* renamed from: b */
    public final erf mo5186b(erf erf, String str, int i) {
        erf erf2;
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) erf);
        a.writeString(str);
        a.writeInt(i);
        Parcel a2 = mo2086a(4, a);
        IBinder readStrongBinder = a2.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            erf2 = queryLocalInterface instanceof erf ? (erf) queryLocalInterface : new erd(readStrongBinder);
        } else {
            erf2 = null;
        }
        a2.recycle();
        return erf2;
    }

    /* renamed from: b */
    public final int mo5184b() {
        Parcel a = mo2086a(6, mo2085a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    /* renamed from: a */
    public final int mo5182a(erf erf, String str, boolean z) {
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) erf);
        a.writeString(str);
        bin.m2620a(a, z);
        Parcel a2 = mo2086a(3, a);
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    /* renamed from: b */
    public final int mo5185b(erf erf, String str, boolean z) {
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) erf);
        a.writeString(str);
        bin.m2620a(a, z);
        Parcel a2 = mo2086a(5, a);
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }
}
