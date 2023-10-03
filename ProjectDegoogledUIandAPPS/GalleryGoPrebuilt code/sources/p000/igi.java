package p000;

/* renamed from: igi */
/* compiled from: PG */
public final class igi extends iix implements ikg {

    /* renamed from: g */
    public static final igi f14099g;

    /* renamed from: h */
    private static volatile ikn f14100h;

    /* renamed from: a */
    public int f14101a;

    /* renamed from: b */
    public int f14102b;

    /* renamed from: c */
    public String f14103c = "";

    /* renamed from: d */
    public int f14104d;

    /* renamed from: e */
    public double f14105e;

    /* renamed from: f */
    public ije f14106f;

    static {
        igi igi = new igi();
        f14099g = igi;
        iix.m13611a(igi.class, (iix) igi);
    }

    private igi() {
        ikq ikq = ikq.f14400b;
        this.f14106f = ikq.f14400b;
        iiy iiy = iiy.f14327b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo2350a(int i, Object obj) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 1) {
            return null;
        }
        if (i2 == 2) {
            return m13609a((ikf) f14099g, "\u0001\u0005\u0000\u0001\u0001\u0012\u0005\u0000\u0001\u0000\u0001\u0004\u0000\u0003\f\u0003\u0007\u0000\u0006\b\u001b\u0012\b\u0001", new Object[]{"a", "b", "d", igp.f14142a, "e", "f", igm.class, "c"});
        } else if (i2 == 3) {
            return new igi();
        } else {
            if (i2 == 4) {
                return new iir((float[]) null, (byte[]) null);
            }
            if (i2 == 5) {
                return f14099g;
            }
            ikn ikn = f14100h;
            if (ikn == null) {
                synchronized (igi.class) {
                    ikn = f14100h;
                    if (ikn == null) {
                        ikn = new iis(f14099g);
                        f14100h = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
