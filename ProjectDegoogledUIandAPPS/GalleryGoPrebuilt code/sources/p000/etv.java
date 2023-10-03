package p000;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;

/* renamed from: etv */
/* compiled from: PG */
public final class etv extends epv {
    public etv(Context context, Looper looper, epk epk, ekt ekt, eku eku) {
        super(context, looper, 63, epk, ekt, eku);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo4883a() {
        return "com.google.android.gms.googlehelp.service.GoogleHelpService.START";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final String mo4884b() {
        return "com.google.android.gms.googlehelp.internal.common.IGoogleHelpService";
    }

    /* renamed from: c */
    public final int mo4885c() {
        return 11925000;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ IInterface mo4882a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.googlehelp.internal.common.IGoogleHelpService");
        if (!(queryLocalInterface instanceof etz)) {
            return new ety(iBinder);
        }
        return (etz) queryLocalInterface;
    }
}
