package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: hpg */
/* compiled from: PG */
public final class hpg implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ iev f13212a;

    /* renamed from: b */
    private final /* synthetic */ Runnable f13213b;

    /* renamed from: c */
    private final /* synthetic */ hpf f13214c;

    /* renamed from: d */
    private final /* synthetic */ iel f13215d;

    /* renamed from: e */
    private final /* synthetic */ long f13216e;

    /* renamed from: f */
    private final /* synthetic */ long f13217f;

    /* renamed from: g */
    private final /* synthetic */ exm f13218g;

    public hpg(iev iev, Runnable runnable, hpf hpf, iel iel, long j, long j2, exm exm) {
        this.f13212a = iev;
        this.f13213b = runnable;
        this.f13214c = hpf;
        this.f13215d = iel;
        this.f13216e = j;
        this.f13217f = j2;
        this.f13218g = exm;
    }

    public final void run() {
        long j;
        try {
            if (!this.f13212a.isDone()) {
                this.f13213b.run();
                hpf hpf = this.f13214c;
                iel iel = this.f13215d;
                long j2 = this.f13216e;
                long j3 = this.f13217f;
                long c = this.f13218g.mo5372c();
                if (c < j2) {
                    j = (j2 + j3) - c;
                } else {
                    j = j3 - ((c - j2) % j3);
                }
                hpf.mo7641a(iel.mo5935a((Runnable) this, j, TimeUnit.MILLISECONDS));
            }
        } catch (Throwable th) {
            this.f13212a.mo6952a(th);
        }
    }
}
