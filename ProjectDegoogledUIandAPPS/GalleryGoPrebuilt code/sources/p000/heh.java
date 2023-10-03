package p000;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* renamed from: heh */
/* compiled from: PG */
final /* synthetic */ class heh implements Callable {

    /* renamed from: a */
    private final hem f12577a;

    /* renamed from: b */
    private final ieh f12578b;

    /* renamed from: c */
    private final fab f12579c;

    /* renamed from: d */
    private final ieh f12580d;

    public heh(hem hem, ieh ieh, fab fab, ieh ieh2) {
        this.f12577a = hem;
        this.f12578b = ieh;
        this.f12579c = fab;
        this.f12580d = ieh2;
    }

    public final Object call() {
        hem hem = this.f12577a;
        ieh ieh = this.f12578b;
        fab fab = this.f12579c;
        ieh ieh2 = this.f12580d;
        hpy hpy = (hpy) ife.m12871b((Future) ieh);
        if (hpy.mo7646a()) {
            fab.mo5434a((String) hpy.mo7647b());
        }
        fab.mo5432a((ezw) new hek(hem, (Map) ife.m12871b((Future) ieh2)));
        return fab;
    }
}
