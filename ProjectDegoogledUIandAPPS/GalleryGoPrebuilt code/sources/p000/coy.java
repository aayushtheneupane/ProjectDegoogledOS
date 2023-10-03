package p000;

import android.net.Uri;
import java.util.concurrent.Executor;

/* renamed from: coy */
/* compiled from: PG */
final /* synthetic */ class coy implements cvl {

    /* renamed from: a */
    private final cpd f5332a;

    public coy(cpd cpd) {
        this.f5332a = cpd;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        hlj a;
        cpd cpd = this.f5332a;
        cyg cyg = (cyg) obj;
        hlj a2 = hnb.m11765a("Thumbnail Media Item");
        try {
            Object[] objArr = {cyg.mo3991O(), cyg.mo3910d(), cyg.mo3923o()};
            coq coq = cpd.f5345d;
            Uri O = cyg.mo3991O();
            cpb cpb = new cpb(cpd);
            a = hnb.m11765a("Process GRID_THUMBNAIL");
            ieh a3 = a.mo7548a(coq.f5321b.mo3289a(O, coq.f5320a.mo3297b().mo1876o(), cpb));
            if (a != null) {
                a.close();
            }
            ieh a4 = a2.mo7548a(gte.m10773a(gte.m10771a(a3, (icf) new coz(cpd, cyg), (Executor) idh.f13918a), Throwable.class, (icf) new cpa(cpd, cyg), (Executor) idh.f13918a));
            if (a2 != null) {
                a2.close();
            }
            return a4;
        } catch (Throwable th) {
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
        throw th;
    }
}
