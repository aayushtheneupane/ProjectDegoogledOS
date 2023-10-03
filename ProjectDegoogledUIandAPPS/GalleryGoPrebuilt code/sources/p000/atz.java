package p000;

/* renamed from: atz */
/* compiled from: PG */
final class atz implements aua, bfv {

    /* renamed from: a */
    private static final C0306lc f1679a = bfx.m2449a(20, new aty());

    /* renamed from: b */
    private final bfy f1680b = bfy.m2451a();

    /* renamed from: c */
    private aua f1681c;

    /* renamed from: d */
    private boolean f1682d;

    /* renamed from: e */
    private boolean f1683e;

    /* renamed from: af */
    public final bfy mo1573af() {
        return this.f1680b;
    }

    /* renamed from: b */
    public final Object mo1605b() {
        return this.f1681c.mo1605b();
    }

    /* renamed from: a */
    public final Class mo1604a() {
        return this.f1681c.mo1604a();
    }

    /* renamed from: c */
    public final int mo1606c() {
        return this.f1681c.mo1606c();
    }

    /* renamed from: a */
    static atz m1660a(aua aua) {
        atz atz = (atz) cns.m4632a((Object) (atz) f1679a.mo1971a());
        atz.f1683e = false;
        atz.f1682d = true;
        atz.f1681c = aua;
        return atz;
    }

    /* renamed from: d */
    public final synchronized void mo1607d() {
        this.f1680b.mo1973b();
        this.f1683e = true;
        if (!this.f1682d) {
            this.f1681c.mo1607d();
            this.f1681c = null;
            f1679a.mo1972a(this);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final synchronized void mo1625e() {
        this.f1680b.mo1973b();
        if (this.f1682d) {
            this.f1682d = false;
            if (this.f1683e) {
                mo1607d();
            }
        } else {
            throw new IllegalStateException("Already unlocked");
        }
    }
}
