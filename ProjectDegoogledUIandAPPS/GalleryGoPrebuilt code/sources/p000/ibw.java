package p000;

import java.util.concurrent.Future;

/* renamed from: ibw */
/* compiled from: PG */
final class ibw implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ieh f13858a;

    /* renamed from: b */
    private final /* synthetic */ int f13859b;

    /* renamed from: c */
    private final /* synthetic */ ibz f13860c;

    public ibw(ibz ibz, ieh ieh, int i) {
        this.f13860c = ibz;
        this.f13858a = ieh;
        this.f13859b = i;
    }

    public final void run() {
        try {
            if (!this.f13858a.isCancelled()) {
                this.f13860c.mo8364a(this.f13859b, (Future) this.f13858a);
            } else {
                ibz ibz = this.f13860c;
                ibz.f13867a = null;
                ibz.cancel(false);
            }
        } finally {
            this.f13860c.mo8365a((hsf) null);
        }
    }
}
