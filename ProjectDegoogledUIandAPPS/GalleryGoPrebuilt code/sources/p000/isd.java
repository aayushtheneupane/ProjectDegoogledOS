package p000;

/* renamed from: isd */
/* compiled from: PG */
public final class isd extends iix implements ikg {

    /* renamed from: d */
    public static final isd f14994d;

    /* renamed from: e */
    private static volatile ikn f14995e;

    /* renamed from: a */
    public int f14996a;

    /* renamed from: b */
    public long f14997b;

    /* renamed from: c */
    public int f14998c;

    static {
        isd isd = new isd();
        f14994d = isd;
        iix.m13611a(isd.class, (iix) isd);
    }

    private isd() {
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
            return m13609a((ikf) f14994d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0000\u0002\f\u0001", new Object[]{"a", "b", "c", irg.f14841a});
        } else if (i2 == 3) {
            return new isd();
        } else {
            if (i2 == 4) {
                return new iir((short[][][]) null, (byte[][]) null);
            }
            if (i2 == 5) {
                return f14994d;
            }
            ikn ikn = f14995e;
            if (ikn == null) {
                synchronized (isd.class) {
                    ikn = f14995e;
                    if (ikn == null) {
                        ikn = new iis(f14994d);
                        f14995e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
