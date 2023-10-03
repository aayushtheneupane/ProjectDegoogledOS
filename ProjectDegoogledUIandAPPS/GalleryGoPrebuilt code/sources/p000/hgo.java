package p000;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: hgo */
/* compiled from: PG */
final /* synthetic */ class hgo implements ice {

    /* renamed from: a */
    private final hgq f12701a;

    public hgo(hgq hgq) {
        this.f12701a = hgq;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        hgq hgq = this.f12701a;
        ArrayList arrayList = new ArrayList();
        for (hgr a : ((hsu) hgq.f12704b).values()) {
            arrayList.add(a.mo6866a());
        }
        return ife.m12866b((Iterable) arrayList).mo8443a(hmq.m11749a((Callable) new hgp(arrayList)), (Executor) hgq.f12705c);
    }
}
