package p000;

/* renamed from: ale */
/* compiled from: PG */
public final class ale implements ala {

    /* renamed from: a */
    private final C0053bx f707a;

    /* renamed from: b */
    private final C0059cc f708b;

    /* renamed from: c */
    private final C0059cc f709c;

    public ale(C0053bx bxVar) {
        this.f707a = bxVar;
        new alb(bxVar);
        this.f708b = new alc(bxVar);
        this.f709c = new ald(bxVar);
    }

    /* renamed from: a */
    public final void mo591a(String str) {
        this.f707a.mo2844d();
        C0037bh b = this.f708b.mo3016b();
        if (str == null) {
            b.mo1920a(1);
        } else {
            b.mo1922a(1, str);
        }
        this.f707a.mo2845e();
        try {
            b.mo2033b();
            this.f707a.mo2847g();
        } finally {
            this.f707a.mo2846f();
            this.f708b.mo3015a(b);
        }
    }

    /* renamed from: a */
    public final void mo590a() {
        this.f707a.mo2844d();
        C0037bh b = this.f709c.mo3016b();
        this.f707a.mo2845e();
        try {
            b.mo2033b();
            this.f707a.mo2847g();
        } finally {
            this.f707a.mo2846f();
            this.f709c.mo3015a(b);
        }
    }
}
