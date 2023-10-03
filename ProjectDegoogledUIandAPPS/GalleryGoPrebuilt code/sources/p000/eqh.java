package p000;

import android.os.IInterface;
import android.os.Parcel;

/* renamed from: eqh */
/* compiled from: PG */
public abstract class eqh extends bim implements eqi {
    public eqh() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        if (i == 1) {
            erf b = mo4922b();
            parcel2.writeNoException();
            bin.m2618a(parcel2, (IInterface) b);
        } else if (i != 2) {
            return false;
        } else {
            int c = mo4923c();
            parcel2.writeNoException();
            parcel2.writeInt(c);
        }
        return true;
    }
}
