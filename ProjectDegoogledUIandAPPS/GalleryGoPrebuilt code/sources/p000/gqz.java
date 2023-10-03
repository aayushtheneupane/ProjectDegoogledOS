package p000;

import java.util.concurrent.Future;

/* renamed from: gqz */
/* compiled from: PG */
final /* synthetic */ class gqz implements Runnable {

    /* renamed from: a */
    private final grf f11867a;

    /* renamed from: b */
    private final iev f11868b;

    /* renamed from: c */
    private final grd f11869c;

    public gqz(grf grf, iev iev, grd grd) {
        this.f11867a = grf;
        this.f11868b = iev;
        this.f11869c = grd;
    }

    public final void run() {
        grf grf = this.f11867a;
        iev iev = this.f11868b;
        grd grd = this.f11869c;
        try {
            grf.f11890d.mo8346b(ife.m12871b((Future) iev));
            grd.mo6946a(grf.f11890d);
        } catch (Throwable th) {
            grd.mo6946a(iev);
        }
    }
}
