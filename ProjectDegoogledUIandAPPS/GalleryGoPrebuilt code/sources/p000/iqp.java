package p000;

/* renamed from: iqp */
/* compiled from: PG */
public final class iqp extends iix implements ikg {

    /* renamed from: d */
    public static final iqp f14676d;

    /* renamed from: e */
    private static volatile ikn f14677e;

    /* renamed from: a */
    public int f14678a;

    /* renamed from: b */
    public int f14679b;

    /* renamed from: c */
    public iqq f14680c;

    static {
        iqp iqp = new iqp();
        f14676d = iqp;
        iix.m13611a(iqp.class, (iix) iqp);
    }

    private iqp() {
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
            return m13609a((ikf) f14676d, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\t\u0001", new Object[]{"a", "b", "c"});
        } else if (i2 == 3) {
            return new iqp();
        } else {
            if (i2 == 4) {
                return new iir((char[][]) null, (char[]) null);
            }
            if (i2 == 5) {
                return f14676d;
            }
            ikn ikn = f14677e;
            if (ikn == null) {
                synchronized (iqp.class) {
                    ikn = f14677e;
                    if (ikn == null) {
                        ikn = new iis(f14676d);
                        f14677e = ikn;
                    }
                }
            }
            return ikn;
        }
    }
}
