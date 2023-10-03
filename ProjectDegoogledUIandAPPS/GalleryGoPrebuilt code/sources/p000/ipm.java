package p000;

/* renamed from: ipm */
/* compiled from: PG */
final class ipm extends ipg {

    /* renamed from: b */
    private final hso f14625b;

    public ipm(hso hso) {
        this.f14625b = hso;
    }

    /* renamed from: a */
    public final void mo9017a(Throwable th) {
        hvs i = this.f14625b.mo7910e().listIterator();
        while (i.hasNext()) {
            ipg ipg = (ipg) i.next();
            try {
                ipg.mo9017a(th);
            } catch (RuntimeException e) {
                ips.m14311a(e, ipg, "failed", th);
            }
        }
    }

    /* renamed from: c */
    public final void mo7601c() {
        hvs i = this.f14625b.mo7910e().listIterator();
        while (i.hasNext()) {
            ipg ipg = (ipg) i.next();
            try {
                ipg.mo7601c();
            } catch (RuntimeException e) {
                ips.m14310a(e, ipg, "methodFinished");
            }
        }
    }

    /* renamed from: b */
    public final void mo7600b() {
        hvs i = this.f14625b.listIterator();
        while (i.hasNext()) {
            ipg ipg = (ipg) i.next();
            try {
                ipg.mo7600b();
            } catch (RuntimeException e) {
                ips.m14310a(e, ipg, "methodStarting");
            }
        }
    }

    /* renamed from: d */
    public final void mo9018d() {
        hvs i = this.f14625b.listIterator();
        while (i.hasNext()) {
            ipg ipg = (ipg) i.next();
            try {
                ipg.mo9018d();
            } catch (RuntimeException e) {
                ips.m14310a(e, ipg, "ready");
            }
        }
    }

    /* renamed from: a */
    public final void mo7599a() {
        hvs i = this.f14625b.listIterator();
        while (i.hasNext()) {
            ipg ipg = (ipg) i.next();
            try {
                ipg.mo7599a();
            } catch (RuntimeException e) {
                ips.m14310a(e, ipg, "requested");
            }
        }
    }

    /* renamed from: a */
    public final void mo9016a(Object obj) {
        hvs i = this.f14625b.mo7910e().listIterator();
        while (i.hasNext()) {
            ipg ipg = (ipg) i.next();
            try {
                ipg.mo9016a(obj);
            } catch (RuntimeException e) {
                ips.m14311a(e, ipg, "succeeded", obj);
            }
        }
    }
}
