package p000;

import java.util.concurrent.Executor;

/* renamed from: idd */
/* compiled from: PG */
final class idd extends idf {

    /* renamed from: b */
    private final ice f13909b;

    /* renamed from: c */
    private final /* synthetic */ idg f13910c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public idd(idg idg, ice ice, Executor executor) {
        super(idg, executor);
        this.f13910c = idg;
        this.f13909b = (ice) ife.m12898e((Object) ice);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo8408b() {
        this.f13913a = false;
        return (ieh) ife.m12829a((Object) this.f13909b.mo2539a(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) this.f13909b);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo8407a(Object obj) {
        this.f13910c.mo6946a((ieh) obj);
    }

    /* renamed from: a */
    public final String mo8406a() {
        return this.f13909b.toString();
    }
}
