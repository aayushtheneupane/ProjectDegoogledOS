package p000;

import java.util.concurrent.Executor;

/* renamed from: chs */
/* compiled from: PG */
final /* synthetic */ class chs implements cvl {

    /* renamed from: a */
    private final chw f4420a;

    public chs(chw chw) {
        this.f4420a = chw;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        chw chw = this.f4420a;
        cia cia = (cia) obj;
        hlj a = hnb.m11765a("Thumbnail person");
        try {
            ieh a2 = gte.m10771a(chw.f4428a.mo3159a((Iterable) hso.m12033a((Object) cia.mo3109c())), (icf) new cht(chw, cia), (Executor) chw.f4431d);
            cwn.m5509a(a2, "FaceThumbnailingJob: Failed to generate thumbnail for person (id=%d)", cia.mo3107a().get());
            ieh a3 = a.mo7548a(gte.m10773a(a2, Throwable.class, (icf) new chu(chw, cia), (Executor) chw.f4431d));
            if (a != null) {
                a.close();
            }
            return a3;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
