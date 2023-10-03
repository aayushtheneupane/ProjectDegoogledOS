package p000;

import android.graphics.Bitmap;

/* renamed from: chv */
/* compiled from: PG */
final /* synthetic */ class chv implements icf {

    /* renamed from: a */
    private final chw f4425a;

    /* renamed from: b */
    private final cia f4426b;

    /* renamed from: c */
    private final cff f4427c;

    public chv(chw chw, cia cia, cff cff) {
        this.f4425a = chw;
        this.f4426b = cia;
        this.f4427c = cff;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        chw chw = this.f4425a;
        cia cia = this.f4426b;
        cff cff = this.f4427c;
        chz g = cia.mo3114g();
        g.mo3147a(((Long) cff.mo3091a().get()).longValue());
        g.mo3151b(true);
        g.mo3150a(cof.m4689a((Bitmap) obj, 75));
        return chw.f4429b.mo3170a(hso.m12033a((Object) g.mo3146a()));
    }
}
