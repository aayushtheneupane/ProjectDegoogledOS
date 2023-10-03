package p000;

/* renamed from: irf */
/* compiled from: PG */
public final class irf extends iix implements ikg {

    /* renamed from: d */
    public static final irf f14836d;

    /* renamed from: e */
    private static volatile ikn f14837e;

    /* renamed from: a */
    public int f14838a;

    /* renamed from: b */
    public int f14839b;

    /* renamed from: c */
    public long f14840c;

    static {
        irf irf = new irf();
        f14836d = irf;
        iix.m13611a(irf.class, (iix) irf);
    }

    private irf() {
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
            return m13609a((ikf) f14836d, "\u0001\u0002\u0000\u0001\u0005\u0006\u0002\u0000\u0000\u0000\u0005\u0004\u0000\u0006\u0002\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new irf();
        } else {
            if (i2 == 4) {
                return new iir((boolean[][][]) null, (float[]) null);
            }
            if (i2 == 5) {
                return f14836d;
            }
            ikn ikn = f14837e;
            if (ikn == null) {
                synchronized (irf.class) {
                    ikn = f14837e;
                    if (ikn == null) {
                        ikn = new iis(f14836d);
                        f14837e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
