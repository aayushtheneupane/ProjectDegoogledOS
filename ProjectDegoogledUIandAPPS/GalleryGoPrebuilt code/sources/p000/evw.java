package p000;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;

/* renamed from: evw */
/* compiled from: PG */
public final class evw extends epv {
    public evw(Context context, Looper looper, epk epk, ekt ekt, eku eku) {
        super(context, looper, 51, epk, ekt, eku);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo4883a() {
        return "com.google.android.gms.phenotype.service.START";
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final String mo4884b() {
        return "com.google.android.gms.phenotype.internal.IPhenotypeService";
    }

    /* renamed from: c */
    public final int mo4885c() {
        return 9410000;
    }

    /* renamed from: t */
    public final ejt[] mo5129t() {
        return eur.f9063a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ IInterface mo4882a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.phenotype.internal.IPhenotypeService");
        if (!(queryLocalInterface instanceof evl)) {
            return new evk(iBinder);
        }
        return (evl) queryLocalInterface;
    }
}
