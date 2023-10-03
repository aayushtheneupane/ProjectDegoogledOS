package p000;

/* renamed from: gqu */
/* compiled from: PG */
final /* synthetic */ class gqu implements Runnable {

    /* renamed from: a */
    private final gqw f11850a;

    public gqu(gqw gqw) {
        this.f11850a = gqw;
    }

    public final void run() {
        gqw gqw = this.f11850a;
        boolean a = gqw.f11857c.mo6891a();
        synchronized (gqw.f11858d) {
            boolean z = false;
            if (!a) {
                try {
                    if (!gqw.f11859e.isEmpty()) {
                        z = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            gqv gqv = gqv.STOPPED;
            int ordinal = gqw.f11861g.ordinal();
            if (ordinal != 0) {
                if (ordinal == 2) {
                    if (!z) {
                        gqw.mo6939a();
                    }
                }
            } else if (z) {
                gqw.mo6940a(gqw.mo6942b((gqy) null).f11865a);
            }
        }
    }
}
