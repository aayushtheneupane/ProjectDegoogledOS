package p000;

/* renamed from: gsf */
/* compiled from: PG */
final /* synthetic */ class gsf implements Runnable {

    /* renamed from: a */
    private final gsg f11953a;

    /* renamed from: b */
    private final gsk f11954b;

    /* renamed from: c */
    private final Throwable f11955c;

    public gsf(gsg gsg, gsk gsk, Throwable th) {
        this.f11953a = gsg;
        this.f11954b = gsk;
        this.f11955c = th;
    }

    public final void run() {
        gsg gsg = this.f11953a;
        gsk gsk = this.f11954b;
        Throwable th = this.f11955c;
        if (gsg.f11959d && gsg.f11958c.remove(gsk)) {
            gry gry = (gry) gsg.f11957b.mo6983a(gsk.f11962a);
            hlj a = hnb.m11767a("onFailure FuturesMixin", hnf.f13084a, hlm.f12987a);
            try {
                gry.mo2651a(gsk.f11964c, th);
                a.close();
                return;
            } catch (Throwable th2) {
                ifn.m12935a(th, th2);
            }
        } else {
            return;
        }
        throw th;
    }
}
