package p000;

import android.graphics.drawable.Drawable;
import android.net.Uri;

/* renamed from: baq */
/* compiled from: PG */
public final class baq implements arb {

    /* renamed from: a */
    private final bbm f1969a;

    /* renamed from: b */
    private final auk f1970b;

    public baq(bbm bbm, auk auk) {
        this.f1969a = bbm;
        this.f1970b = auk;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ aua mo1507a(Object obj, int i, int i2, aqz aqz) {
        aua a = this.f1969a.mo1777a((Uri) obj);
        if (a == null) {
            return null;
        }
        return bae.m2022a(this.f1970b, (Drawable) a.mo1605b(), i, i2);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1508a(Object obj, aqz aqz) {
        return "android.resource".equals(((Uri) obj).getScheme());
    }
}
