package p000;

import java.util.concurrent.Executor;

/* renamed from: cgm */
/* compiled from: PG */
final /* synthetic */ class cgm implements cvl {

    /* renamed from: a */
    private final cgr f4333a;

    /* renamed from: b */
    private final cgt f4334b;

    public cgm(cgr cgr, cgt cgt) {
        this.f4333a = cgr;
        this.f4334b = cgt;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        cgr cgr = this.f4333a;
        cgt cgt = this.f4334b;
        cyg cyg = (cyg) obj;
        if (cyg.mo3901H()) {
            return ife.m12820a((Object) null);
        }
        hlj a = hnb.m11765a("Process image for faces");
        try {
            new Object[1][0] = cyg.mo3907a().get();
            ieh a2 = cgr.f4350e.mo3289a(cyg.mo3991O(), cgr.f4351f.mo3298b(800, 800), new cgn(cgr, cgt, cyg));
            cwn.m5509a(a2, "Face detection failed for media item (id=%d)", cyg.mo3907a().get());
            ieh a3 = a.mo7548a(gte.m10773a(a2, Throwable.class, (icf) new cgo(cgr, cyg), (Executor) cgr.f4352g));
            if (a == null) {
                return a3;
            }
            a.close();
            return a3;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
