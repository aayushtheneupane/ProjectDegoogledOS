package p000;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;

/* renamed from: eqj */
/* compiled from: PG */
public final class eqj extends bim implements eqk {

    /* renamed from: a */
    private epi f8838a;

    /* renamed from: b */
    private final int f8839b;

    public eqj(epi epi, int i) {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
        this.f8838a = epi;
        this.f8839b = i;
    }

    /* renamed from: a */
    private final void m8037a(int i, IBinder iBinder, Bundle bundle) {
        abj.m86a((Object) this.f8838a, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
        this.f8838a.mo5110a(i, iBinder, bundle, this.f8839b);
        this.f8838a = null;
    }

    public eqj() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        if (i == 1) {
            m8037a(parcel.readInt(), parcel.readStrongBinder(), (Bundle) bin.m2617a(parcel, Bundle.CREATOR));
        } else if (i == 2) {
            parcel.readInt();
            Bundle bundle = (Bundle) bin.m2617a(parcel, Bundle.CREATOR);
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        } else if (i != 3) {
            return false;
        } else {
            int readInt = parcel.readInt();
            IBinder readStrongBinder = parcel.readStrongBinder();
            epm epm = (epm) bin.m2617a(parcel, epm.CREATOR);
            abj.m86a((Object) this.f8838a, (Object) "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            abj.m85a((Object) epm);
            this.f8838a.f8761o = epm;
            m8037a(readInt, readStrongBinder, epm.f8784a);
        }
        parcel2.writeNoException();
        return true;
    }
}
