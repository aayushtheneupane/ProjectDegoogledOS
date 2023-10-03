package p000;

import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: hgq */
/* compiled from: PG */
public final class hgq implements hhb {

    /* renamed from: a */
    public static final hvy f12703a = hvy.m12261a("com/google/apps/tiktok/storage/wipeout/WipeoutSynclet");

    /* renamed from: b */
    public final Map f12704b;

    /* renamed from: c */
    public final iek f12705c;

    public hgq(Map map, iek iek) {
        this.f12704b = map;
        this.f12705c = iek;
    }

    /* renamed from: a */
    public final ieh mo6873a() {
        return ife.m12816a(hmq.m11743a((ice) new hgo(this)), (Executor) this.f12705c);
    }
}
