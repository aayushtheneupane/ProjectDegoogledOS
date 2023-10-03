package p000;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: goe */
/* compiled from: PG */
public final class goe implements hhb {

    /* renamed from: a */
    public static final hvy f11724a = hvy.m12261a("com/google/apps/tiktok/cache/OrphanCacheAccountSynclet");

    /* renamed from: b */
    public final Map f11725b;

    /* renamed from: c */
    private final gnn f11726c;

    /* renamed from: d */
    private final iek f11727d;

    public goe(Map map, gnn gnn, iek iek) {
        this.f11725b = map;
        this.f11726c = gnn;
        this.f11727d = iek;
    }

    /* renamed from: a */
    private final ieh m10557a(hfs hfs) {
        gnn gnn = this.f11726c;
        return ibv.m12657a(gnn.f11696c.mo5933a((Callable) new gnm(gnn, hfs)), (hpr) new goc(this), (Executor) this.f11727d);
    }

    /* renamed from: a */
    public final ieh mo6873a() {
        return ife.m12884c(m10557a(hfs.m11385a(1)), m10557a(hfs.m11385a(2))).mo8443a(ife.m12887c(), (Executor) this.f11727d);
    }
}
