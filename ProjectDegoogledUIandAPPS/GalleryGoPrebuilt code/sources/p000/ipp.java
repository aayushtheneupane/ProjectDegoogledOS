package p000;

/* renamed from: ipp */
/* compiled from: PG */
final class ipp extends ipg {

    /* renamed from: b */
    private final ipg f14628b;

    public ipp(ipg ipg) {
        this.f14628b = ipg;
    }

    /* renamed from: a */
    public final void mo9017a(Throwable th) {
        try {
            this.f14628b.mo9017a(th);
        } catch (RuntimeException e) {
            ips.m14311a(e, this.f14628b, "failed", th);
        }
    }

    /* renamed from: c */
    public final void mo7601c() {
        try {
            this.f14628b.mo7601c();
        } catch (RuntimeException e) {
            ips.m14310a(e, this.f14628b, "methodFinished");
        }
    }

    /* renamed from: b */
    public final void mo7600b() {
        try {
            this.f14628b.mo7600b();
        } catch (RuntimeException e) {
            ips.m14310a(e, this.f14628b, "methodStarting");
        }
    }

    /* renamed from: d */
    public final void mo9018d() {
        try {
            this.f14628b.mo9018d();
        } catch (RuntimeException e) {
            ips.m14310a(e, this.f14628b, "ready");
        }
    }

    /* renamed from: a */
    public final void mo7599a() {
        try {
            this.f14628b.mo7599a();
        } catch (RuntimeException e) {
            ips.m14310a(e, this.f14628b, "requested");
        }
    }

    /* renamed from: a */
    public final void mo9016a(Object obj) {
        try {
            this.f14628b.mo9016a(obj);
        } catch (RuntimeException e) {
            ips.m14311a(e, this.f14628b, "succeeded", obj);
        }
    }
}
