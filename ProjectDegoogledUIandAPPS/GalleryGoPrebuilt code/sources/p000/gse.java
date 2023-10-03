package p000;

/* renamed from: gse */
/* compiled from: PG */
final /* synthetic */ class gse implements Runnable {

    /* renamed from: a */
    private final gsg f11950a;

    /* renamed from: b */
    private final gsk f11951b;

    /* renamed from: c */
    private final Object f11952c;

    public gse(gsg gsg, gsk gsk, Object obj) {
        this.f11950a = gsg;
        this.f11951b = gsk;
        this.f11952c = obj;
    }

    public final void run() {
        gsg gsg = this.f11950a;
        gsk gsk = this.f11951b;
        Object obj = this.f11952c;
        if (gsg.f11959d && gsg.f11958c.remove(gsk)) {
            gry gry = (gry) gsg.f11957b.mo6983a(gsk.f11962a);
            hlj a = hnb.m11767a("onSuccess FuturesMixin", hnf.f13084a, hlm.f12987a);
            try {
                gry.mo2650a(gsk.f11964c, obj);
                a.close();
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
