package p000;

/* renamed from: fdj */
/* compiled from: PG */
public abstract class fdj {

    /* renamed from: a */
    private final fdo f9316a;

    /* renamed from: b */
    private final fdn f9317b = fdr.f9324a;

    /* renamed from: c */
    private fdr f9318c = null;

    /* renamed from: d */
    private final iit f9319d;

    protected fdj(int i, fdo fdo, ffa ffa) {
        ife.m12898e((Object) ffa);
        ife.m12890c(true);
        this.f9316a = fdo;
        iit iit = (iit) fdx.f9338e.mo8793g();
        if (iit.f14319c) {
            iit.mo8751b();
            iit.f14319c = false;
        }
        fdx fdx = (fdx) iit.f14318b;
        fdx.f9340a = 1 | fdx.f9340a;
        fdx.f9341b = i;
        this.f9319d = iit;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract fdj mo5511b();

    /* renamed from: a */
    public final fdj mo5512a(fdm fdm) {
        ife.m12896d(this.f9318c == null);
        ife.m12896d(!((((fdx) this.f9319d.f14318b).f9340a & 2) != 0));
        if (!this.f9319d.mo8784a(fdm.f9320a)) {
            iit iit = this.f9319d;
            int a = fdm.f9320a.mo8710a();
            if (iit.f14319c) {
                iit.mo8751b();
                iit.f14319c = false;
            }
            fdx fdx = (fdx) iit.f14318b;
            fdx fdx2 = fdx.f9338e;
            if (!fdx.f9342c.mo8521a()) {
                fdx.f9342c = iix.m13606a(fdx.f9342c);
            }
            fdx.f9342c.mo8801d(a);
        }
        this.f9319d.mo8783a(fdm.f9320a, fdm.f9321b);
        return mo5511b();
    }

    /* renamed from: a */
    public final fdj mo5513a(fdp fdp) {
        ife.m12896d(this.f9318c == null);
        this.f9319d.mo8783a(fdp.f9322a, fdp.f9323b);
        return mo5511b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final fdr mo5514c() {
        ife.m12876b(this.f9318c == null, (Object) "Cannot create CVE twice.");
        fdr fdr = new fdr(this.f9319d, this.f9317b, this.f9316a);
        this.f9318c = fdr;
        ife.m12898e((Object) ffa.f9433a);
        fdo fdo = fdr.f9325b;
        if (fdo != null) {
            fdo.mo5516a(fdr);
        }
        return this.f9318c;
    }
}
