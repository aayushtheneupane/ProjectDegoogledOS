package p000;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: fcn */
/* compiled from: PG */
final /* synthetic */ class fcn implements ice {

    /* renamed from: a */
    private final fcp f9270a;

    /* renamed from: b */
    private final fcx f9271b;

    public fcn(fcp fcp, fcx fcx) {
        this.f9270a = fcp;
        this.f9271b = fcx;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        fcp fcp = this.f9270a;
        fcx fcx = this.f9271b;
        ArrayList arrayList = new ArrayList(1);
        hvr a = ((hvo) fcp.f9274b).iterator();
        while (a.hasNext()) {
            arrayList.add(((fcq) a.next()).mo5487a(fcx));
        }
        return ife.m12883c((Iterable) arrayList).mo8443a((Callable) new fco(arrayList), (Executor) fcp.f9273a);
    }
}
