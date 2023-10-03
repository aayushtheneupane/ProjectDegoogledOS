package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: gpq */
/* compiled from: PG */
final class gpq implements Runnable {

    /* renamed from: a */
    public final Runnable f11804a = this;

    /* renamed from: b */
    public final /* synthetic */ gpt f11805b;

    /* renamed from: c */
    private final /* synthetic */ Runnable f11806c;

    /* renamed from: d */
    private final /* synthetic */ iev f11807d;

    /* renamed from: e */
    private final /* synthetic */ gps f11808e;

    /* renamed from: f */
    private final /* synthetic */ long f11809f;

    /* renamed from: g */
    private final /* synthetic */ TimeUnit f11810g;

    public gpq(gpt gpt, Runnable runnable, iev iev, gps gps, long j, TimeUnit timeUnit) {
        this.f11805b = gpt;
        this.f11806c = runnable;
        this.f11807d = iev;
        this.f11808e = gps;
        this.f11809f = j;
        this.f11810g = timeUnit;
    }

    public final void run() {
        this.f11805b.execute(new gpp(this, this.f11806c, this.f11807d, this.f11808e, this.f11809f, this.f11810g));
    }
}
