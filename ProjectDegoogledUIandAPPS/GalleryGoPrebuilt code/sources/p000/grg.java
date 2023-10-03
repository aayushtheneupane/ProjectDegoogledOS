package p000;

import java.util.concurrent.RejectedExecutionException;

/* renamed from: grg */
/* compiled from: PG */
public final class grg implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ grj f11893a;

    public grg(grj grj) {
        this.f11893a = grj;
    }

    public final void run() {
        while (!this.f11893a.f11899c.isShutdown()) {
            try {
                grh grh = ((gri) this.f11893a.f11898b.remove()).f11896a;
                int i = grh.f11894a;
                grh.mo8346b((Object) null);
            } catch (InterruptedException e) {
                try {
                    this.f11893a.f11899c.execute(this);
                    return;
                } catch (RejectedExecutionException e2) {
                    for (gri gri : grj.f11897a.keySet()) {
                        gri.f11896a.mo6952a(e2);
                    }
                    return;
                }
            } catch (Throwable th) {
                try {
                    this.f11893a.f11899c.execute(this);
                } catch (RejectedExecutionException e3) {
                    for (gri gri2 : grj.f11897a.keySet()) {
                        gri2.f11896a.mo6952a(e3);
                    }
                }
                throw th;
            }
        }
        try {
            this.f11893a.f11899c.execute(this);
        } catch (RejectedExecutionException e4) {
            for (gri gri3 : grj.f11897a.keySet()) {
                gri3.f11896a.mo6952a(e4);
            }
        }
    }
}
