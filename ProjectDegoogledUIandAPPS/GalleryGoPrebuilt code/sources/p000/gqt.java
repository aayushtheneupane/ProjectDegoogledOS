package p000;

/* renamed from: gqt */
/* compiled from: PG */
public final /* synthetic */ class gqt implements Runnable {

    /* renamed from: a */
    private final gqw f11848a;

    /* renamed from: b */
    private final ieh f11849b;

    public gqt(gqw gqw, ieh ieh) {
        this.f11848a = gqw;
        this.f11849b = ieh;
    }

    public final void run() {
        gqw gqw = this.f11848a;
        ieh ieh = this.f11849b;
        synchronized (gqw.f11858d) {
            gqy gqy = (gqy) gqw.f11859e.remove(ieh);
            gqv gqv = gqv.STOPPED;
            if (gqw.f11861g.ordinal() == 2 && gqy == gqw.f11863i) {
                if (!gqw.f11859e.isEmpty()) {
                    gqw.mo6941a((gqy) null);
                } else {
                    gqw.mo6939a();
                }
            }
        }
    }
}
