package p000;

import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: cvv */
/* compiled from: PG */
public final class cvv implements gsw {

    /* renamed from: a */
    public final cvx f5754a;

    /* renamed from: b */
    public final cui f5755b;

    /* renamed from: c */
    private final Map f5756c;

    /* renamed from: d */
    private final iel f5757d;

    public cvv(iel iel, Map map, cvx cvx, cui cui) {
        this.f5757d = iel;
        this.f5756c = map;
        this.f5754a = cvx;
        this.f5755b = cui;
    }

    /* renamed from: a */
    public final ieh mo3844a() {
        if (!this.f5755b.mo3834a(cuh.CONTENT_UPDATE_WORKER)) {
            return ife.m12820a((Object) ihg.m13029a());
        }
        ieh a = ife.m12820a((Object) null);
        for (cvp cvp : cvp.values()) {
            cvo cvo = (cvo) this.f5756c.get(cvp);
            if (cvo != null) {
                if (cvp.ordinal() == 0) {
                    hlj a2 = hnb.m11765a("Sync after content update");
                    try {
                        a = a2.mo7548a(gte.m10769a(a).mo7611a((ice) new cvt(cvo), (Executor) this.f5757d));
                        cwn.m5509a(a, "ContentUpdateWorker: Failed to run job %s.", cvp);
                        if (a2 != null) {
                            a2.close();
                        }
                    } catch (Throwable th) {
                        ifn.m12935a(th, th);
                    }
                } else {
                    String valueOf = String.valueOf(cvp);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
                    sb.append("Unknown type ");
                    sb.append(valueOf);
                    throw new UnsupportedOperationException(sb.toString());
                }
            }
        }
        ieh a3 = gte.m10770a(a, cvu.f5753a, (Executor) this.f5757d);
        a3.mo53a(new cvq(this), this.f5757d);
        return gte.m10771a(gte.m10771a(a3, (icf) new cvr(this), (Executor) this.f5757d), (icf) new cvs(a3), (Executor) this.f5757d);
        throw th;
    }
}
