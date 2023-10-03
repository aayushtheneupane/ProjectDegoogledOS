package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: gpp */
/* compiled from: PG */
final /* synthetic */ class gpp implements Runnable {

    /* renamed from: a */
    private final gpq f11798a;

    /* renamed from: b */
    private final Runnable f11799b;

    /* renamed from: c */
    private final iev f11800c;

    /* renamed from: d */
    private final gps f11801d;

    /* renamed from: e */
    private final long f11802e;

    /* renamed from: f */
    private final TimeUnit f11803f;

    public gpp(gpq gpq, Runnable runnable, iev iev, gps gps, long j, TimeUnit timeUnit) {
        this.f11798a = gpq;
        this.f11799b = runnable;
        this.f11800c = iev;
        this.f11801d = gps;
        this.f11802e = j;
        this.f11803f = timeUnit;
    }

    public final void run() {
        gpq gpq = this.f11798a;
        Runnable runnable = this.f11799b;
        iev iev = this.f11800c;
        gps gps = this.f11801d;
        long j = this.f11802e;
        TimeUnit timeUnit = this.f11803f;
        try {
            runnable.run();
            if (!iev.isDone()) {
                iej a = gpq.f11805b.f11813a.mo5935a(gpq.f11804a, j, timeUnit);
                gps.f11812a = a;
                if (gps.isDone()) {
                    a.cancel(false);
                }
            }
        } catch (Throwable th) {
            iev.mo6952a(th);
        }
    }
}
