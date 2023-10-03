package p000;

/* renamed from: grl */
/* compiled from: PG */
final class grl implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ grm f11903a;

    public /* synthetic */ grl(grm grm) {
        this.f11903a = grm;
    }

    public final void run() {
        Runnable runnable;
        try {
            synchronized (this.f11903a.f11904a) {
                ife.m12896d(!this.f11903a.f11906c);
                this.f11903a.f11906c = true;
            }
            while (true) {
                synchronized (this.f11903a.f11904a) {
                    grm grm = this.f11903a;
                    if (!grm.f11907d) {
                        runnable = (Runnable) grm.f11905b.poll();
                    } else {
                        runnable = null;
                    }
                    if (runnable == null) {
                        this.f11903a.f11909f = null;
                        synchronized (this.f11903a.f11904a) {
                            this.f11903a.f11909f = null;
                            ife.m12896d(this.f11903a.f11906c);
                            this.f11903a.f11906c = false;
                        }
                        return;
                    }
                }
                runnable.run();
            }
        } catch (Throwable th) {
            synchronized (this.f11903a.f11904a) {
                this.f11903a.f11909f = null;
                ife.m12896d(this.f11903a.f11906c);
                this.f11903a.f11906c = false;
                throw th;
            }
        }
    }
}
