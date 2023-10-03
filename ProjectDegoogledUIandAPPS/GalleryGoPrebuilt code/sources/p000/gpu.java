package p000;

import java.util.concurrent.ExecutorService;

/* renamed from: gpu */
/* compiled from: PG */
final class gpu extends gpx {

    /* renamed from: a */
    private final /* synthetic */ iel f11815a;

    public gpu(iel iel) {
        this.f11815a = iel;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ iek mo6914a() {
        return this.f11815a;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ExecutorService mo6915b() {
        return this.f11815a;
    }

    /* renamed from: c */
    public final /* bridge */ /* synthetic */ Object mo6916c() {
        return this.f11815a;
    }

    /* renamed from: d */
    public final iel mo6921d() {
        return this.f11815a;
    }

    public final void execute(Runnable runnable) {
        this.f11815a.execute(new gpv(runnable));
    }
}
