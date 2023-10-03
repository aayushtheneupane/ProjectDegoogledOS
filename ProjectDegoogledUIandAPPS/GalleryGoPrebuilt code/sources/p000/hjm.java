package p000;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: hjm */
/* compiled from: PG */
public final class hjm {

    /* renamed from: a */
    public final exm f12859a;

    /* renamed from: b */
    private final hiz f12860b;

    /* renamed from: c */
    private final iek f12861c;

    /* renamed from: d */
    private final Random f12862d;

    public hjm(exm exm, hiz hiz, iek iek, Random random) {
        this.f12859a = exm;
        this.f12860b = hiz;
        this.f12861c = iek;
        this.f12862d = random;
    }

    /* renamed from: a */
    public final long mo7498a(long j, long j2) {
        return j + (Math.abs(this.f12862d.nextLong()) % j2);
    }

    /* renamed from: a */
    public final ieh mo7499a(Set set, long j, Map map) {
        return ibv.m12657a(this.f12860b.mo7489b(), hmq.m11742a((hpr) new hjl(this, map, set, j)), (Executor) this.f12861c);
    }
}
