package p000;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.googlehelp.GoogleHelp;

/* renamed from: ety */
/* compiled from: PG */
public final class ety extends bil implements etz {
    public ety(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.googlehelp.internal.common.IGoogleHelpService");
    }

    /* renamed from: a */
    public final void mo5241a(GoogleHelp googleHelp, etx etx) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) googleHelp);
        bin.m2619a(a, (Parcelable) null);
        bin.m2618a(a, (IInterface) etx);
        mo2088b(2, a);
    }

    /* renamed from: a */
    public final void mo5242a(esi esi, Bundle bundle, long j, GoogleHelp googleHelp, etx etx) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) esi);
        bin.m2619a(a, (Parcelable) bundle);
        a.writeLong(j);
        bin.m2619a(a, (Parcelable) googleHelp);
        bin.m2618a(a, (IInterface) etx);
        mo2089c(10, a);
    }

    /* renamed from: a */
    public final void mo5240a(Bundle bundle, long j, GoogleHelp googleHelp, etx etx) {
        Parcel a = mo2085a();
        bin.m2619a(a, (Parcelable) bundle);
        a.writeLong(j);
        bin.m2619a(a, (Parcelable) googleHelp);
        bin.m2618a(a, (IInterface) etx);
        mo2089c(9, a);
    }
}
