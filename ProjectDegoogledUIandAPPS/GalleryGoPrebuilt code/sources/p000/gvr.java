package p000;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: gvr */
/* compiled from: PG */
final /* synthetic */ class gvr implements Runnable {

    /* renamed from: a */
    private final gvu f12150a;

    /* renamed from: b */
    private final gva f12151b;

    /* renamed from: c */
    private final guk f12152c;

    public gvr(gvu gvu, gva gva, guk guk) {
        this.f12150a = gvu;
        this.f12151b = gva;
        this.f12152c = guk;
    }

    public final void run() {
        gvu gvu = this.f12150a;
        gva gva = this.f12151b;
        guk guk = this.f12152c;
        if (gvu.f12163i.mo7093a(guk) && !guk.f12077a.isCancelled()) {
            fxk.m9830b();
            try {
                ife.m12871b((Future) guk.f12077a);
                if ((((gty) gva).f12053a.mo2735c() instanceof guq) && gva.mo7106a(gvu.f12160f)) {
                    gvu.f12165k.mo7097a(ife.m12820a((Object) null), ((gty) gva).f12053a.mo2735c(), guw.f12099a, hph.f13219a, gvu.f12156b);
                } else if (!gva.mo7106a(gvu.f12160f)) {
                    gvu.mo7125b(gva);
                    gus gus = gvu.f12165k;
                    ieh a = ife.m12820a((Object) null);
                    Object c = ((gty) gva).f12053a.mo2735c();
                    gux gux = gvu.f12158d;
                    gus.mo7097a(a, c, guw.f12099a, hpy.m11893b(gux), gvu.f12156b);
                } else if (gvu.f12162h.mo7071c() && gvu.mo7124a()) {
                    ife.m12876b(gvu.f12162h.mo7072d().mo7646a(), (Object) "Completed load, fetch is still open, and the callbacks didn't receive data. This is an impossible state.");
                    gvu.m10901b((gua) gvu.f12162h.mo7070b());
                    gvu.f12162h = gvu.f12162h.mo7110a(false);
                }
            } catch (ExecutionException e) {
                gvu.mo7123a(e.getCause());
            } catch (Throwable th) {
                gvu.f12157c.execute(new gvo(th));
            }
        }
    }
}
