package p000;

import android.os.Build;
import android.os.ParcelFileDescriptor;

/* renamed from: ban */
/* compiled from: PG */
public final class ban implements arb {

    /* renamed from: a */
    private final bac f1962a;

    public ban(bac bac) {
        this.f1962a = bac;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ aua mo1507a(Object obj, int i, int i2, aqz aqz) {
        bac bac = this.f1962a;
        return bac.mo1753a((bal) new bak((ParcelFileDescriptor) obj, bac.f1939e, bac.f1938d), i, i2, aqz, bac.f1934c);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1508a(Object obj, aqz aqz) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) obj;
        int i = Build.VERSION.SDK_INT;
        return true;
    }
}
