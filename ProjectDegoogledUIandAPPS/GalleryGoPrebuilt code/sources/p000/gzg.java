package p000;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: gzg */
/* compiled from: PG */
final /* synthetic */ class gzg implements icf {

    /* renamed from: a */
    private final gzn f12347a;

    /* renamed from: b */
    private final ieh f12348b;

    public gzg(gzn gzn, ieh ieh) {
        this.f12347a = gzn;
        this.f12348b = ieh;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        gzn gzn = this.f12347a;
        ieh ieh = this.f12348b;
        Boolean bool = (Boolean) obj;
        if (bool.booleanValue()) {
            return ibv.m12657a(gzn.f12362h.mo7197a(((gwz) ife.m12871b((Future) ieh)).mo7152a().mo7158b()), (hpr) new gzc(bool), (Executor) idh.f13918a);
        }
        return ife.m12820a((Object) bool);
    }
}
