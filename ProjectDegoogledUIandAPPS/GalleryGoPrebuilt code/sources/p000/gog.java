package p000;

import android.content.Context;
import java.util.Map;

/* renamed from: gog */
/* compiled from: PG */
public final class gog implements hhb {

    /* renamed from: a */
    public static final hvy f11729a = hvy.m12261a("com/google/apps/tiktok/cache/OrphanCacheSingletonSynclet");

    /* renamed from: b */
    public final Context f11730b;

    /* renamed from: c */
    public final Map f11731c;

    /* renamed from: d */
    private final iek f11732d;

    public gog(Context context, Map map, iek iek) {
        this.f11730b = context;
        this.f11731c = map;
        this.f11732d = iek;
    }

    /* renamed from: a */
    public final ieh mo6873a() {
        return this.f11732d.mo5931a((Runnable) new gof(this));
    }
}
