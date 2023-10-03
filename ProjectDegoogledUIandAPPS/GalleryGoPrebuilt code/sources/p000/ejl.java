package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: ejl */
/* compiled from: PG */
public final class ejl extends bil implements ejm {
    public ejl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
    }

    /* renamed from: a */
    public final void mo4887a(ejk ejk, eje eje) {
        Parcel a = mo2085a();
        bin.m2618a(a, (IInterface) ejk);
        bin.m2619a(a, (Parcelable) eje);
        mo2089c(1, a);
    }
}
