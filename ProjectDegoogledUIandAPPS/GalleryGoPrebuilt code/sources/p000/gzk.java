package p000;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: gzk */
/* compiled from: PG */
final /* synthetic */ class gzk implements ice {

    /* renamed from: a */
    private final gzn f12352a;

    /* renamed from: b */
    private final ieh f12353b;

    public gzk(gzn gzn, ieh ieh) {
        this.f12352a = gzn;
        this.f12353b = ieh;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        gzn gzn = this.f12352a;
        try {
            return ibd.m12603a(gzn.mo7222a((gwx) ife.m12871b((Future) this.f12353b)), ijh.class, hmq.m11742a((hpr) new gzb(gzn)), (Executor) idh.f13918a);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof IOException) {
                return ife.m12820a((Object) gwz.m10988a(gwy.m10983a("", "", (ihw) null, 0), hsu.m12069a(gzn.f12356b)));
            }
            return ife.m12822a(e.getCause());
        }
    }
}
